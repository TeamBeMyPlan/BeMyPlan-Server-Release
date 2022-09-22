package com.deploy.bemyplan.service.payment.impl;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.common.exception.model.ValidationException;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.payment.Payment;
import com.deploy.bemyplan.domain.payment.PaymentRepository;
import com.deploy.bemyplan.domain.payment.PaymentState;
import com.deploy.bemyplan.external.client.apple.purchase.dto.response.AppStoreResponse;
import com.deploy.bemyplan.external.client.apple.purchase.dto.response.InApp;
import com.deploy.bemyplan.service.payment.PaymentService;
import com.deploy.bemyplan.service.payment.dto.request.ConfirmOrderDto;
import com.deploy.bemyplan.service.payment.dto.request.UserReceiptDto;
import com.deploy.bemyplan.service.payment.dto.response.InAppPurchaseResponse;
import com.deploy.bemyplan.service.payment.utils.AppleInAppPurchaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.deploy.bemyplan.common.exception.ErrorCode.NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Service
public class ApplePaymentService implements PaymentService {
    private final AppleInAppPurchaseValidator appleInAppPurchaseValidator;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    @Override
    public InAppPurchaseResponse purchaseValidate(final Long orderId, final UserReceiptDto userReceipt) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 주문 내역 입니다.", NOT_FOUND_EXCEPTION));

        final AppStoreResponse response = appleInAppPurchaseValidator.appleInAppPurchaseVerify(userReceipt)
                .orElseThrow(() -> new NotFoundException("검증 되지 않은 응답입니다.", NOT_FOUND_EXCEPTION));

        final String transactionId = getTransactionId(response);

        final Payment payment = findOrCreatePayment(order, transactionId, PaymentState.fromCode(response.getStatus()));
        paymentRepository.save(payment);

        return InAppPurchaseResponse.of(payment.getId(), payment.getPaymentState(), payment.getTransactionId());
    }

    @Transactional
    @Override
    public void purchaseConfirm(final Long orderId, final ConfirmOrderDto confirmOrderDto) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 주문 내역 입니다.", NOT_FOUND_EXCEPTION));

        final Payment payment = paymentRepository.findById(confirmOrderDto.getPaymentId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 결제 내역 입니다.", NOT_FOUND_EXCEPTION));

        if (!Objects.equals(orderId, payment.getOrder().getId()) || PaymentState.COMPLETE != payment.getPaymentState())
            throw new ValidationException("결제 처리가 정상적으로 되지 않았습니다.");

        order.orderComplete(payment);
    }

    private Payment findOrCreatePayment(final Order order, final String transactionId, final PaymentState paymentState) {
        return paymentRepository.findByTransactionId(transactionId)
                .orElseGet(() -> Payment.of(order, transactionId, paymentState));
    }

    private String getTransactionId(final AppStoreResponse response) {
        final List<InApp> inAppData = response.getReceipt().getInApp();

        final InApp app = inAppData.stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("트랜잭션 Id가 없습니다."));

        return app.getTransactionId();
    }
}
