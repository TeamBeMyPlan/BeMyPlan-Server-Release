package com.deploy.bemyplan.payment.service.impl;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.common.exception.model.ValidationException;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.payment.Payment;
import com.deploy.bemyplan.domain.payment.PaymentRepository;
import com.deploy.bemyplan.domain.payment.PaymentState;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.payment.service.PaymentService;
import com.deploy.bemyplan.payment.service.dto.request.ConfirmOrderDto;
import com.deploy.bemyplan.payment.service.dto.request.UserReceiptDto;
import com.deploy.bemyplan.payment.service.dto.response.InAppPurchaseResponse;
import com.deploy.bemyplan.payment.service.utils.AppleInAppPurchaseValidator;
import com.deploy.bemyplan.payment.service.utils.dto.response.AppStoreResponse;
import com.deploy.bemyplan.payment.service.utils.dto.response.InApp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class ApplePaymentService implements PaymentService {
    private final AppleInAppPurchaseValidator appleInAppPurchaseValidator;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public InAppPurchaseResponse purchaseValidate(final Long userId, final Long orderId, final UserReceiptDto userReceipt) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 주문 내역 입니다."));

        if (!Objects.equals(order.getUserId(), user.getId())) {
            log.info(order.getUserId() + "sjfksldfjdlkfjsdklfsdj \nsdklfjsdlkf\n" + user.getId());
            throw new ValidationException("잘못된 주문입니다. 구매한 유저가 다릅니다.");
        }
        final AppStoreResponse response = appleInAppPurchaseValidator.appleInAppPurchaseVerify(userReceipt)
                .orElseThrow(() -> new NotFoundException("검증 되지 않은 응답입니다."));

        final String transactionId = getTransactionId(response);

        final Payment payment = findOrCreatePayment(order, transactionId, PaymentState.fromCode(response.getStatus()));
        paymentRepository.save(payment);

        return InAppPurchaseResponse.of(payment.getId(), payment.getPaymentState(), payment.getTransactionId());
    }

    @Transactional
    @Override
    public void purchaseConfirm(final Long orderId, final ConfirmOrderDto confirmOrderDto) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 주문 내역 입니다."));

        final Payment payment = paymentRepository.findById(confirmOrderDto.getPaymentId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 결제 내역 입니다."));
        log.info(payment.getOrder().getId() + "id id id" + order.getId());

        if (!Objects.equals(orderId, payment.getOrder().getId()) || PaymentState.COMPLETE != payment.getPaymentState())
            throw new ValidationException("결제 처리가 정상적으로 되지 않았습니다.");

        order.orderComplete(payment);
    }

    @Transactional
    @Override
    public void purchaseRevert(final Long userId) {
        final List<Order> orderList = orderRepository.findAllByUserId(userId);

        orderRepository.deleteAll(orderList);
    }

    private Payment findOrCreatePayment(final Order order, final String transactionId, final PaymentState paymentState) {
        return Payment.of(order, transactionId, paymentState);
    }

    private String getTransactionId(final AppStoreResponse response) {
        final List<InApp> inAppData = response.getReceipt().getInApp();

        final InApp app = inAppData.stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("트랜잭션 Id가 없습니다."));

        return app.getTransactionId();
    }
}
