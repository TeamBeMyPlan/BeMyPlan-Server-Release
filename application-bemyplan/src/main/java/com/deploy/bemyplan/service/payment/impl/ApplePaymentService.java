package com.deploy.bemyplan.service.payment.impl;

import com.deploy.bemyplan.common.exception.model.ConflictException;
import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.payment.Payment;
import com.deploy.bemyplan.domain.payment.PaymentRepository;
import com.deploy.bemyplan.domain.payment.PaymentState;
import com.deploy.bemyplan.service.payment.PaymentService;
import com.deploy.bemyplan.service.payment.dto.request.UserReceiptDto;
import com.deploy.bemyplan.external.client.apple.purchase.dto.response.AppStoreResponse;
import com.deploy.bemyplan.external.client.apple.purchase.dto.response.InApp;
import com.deploy.bemyplan.service.payment.dto.response.InAppPurchaseResponse;
import com.deploy.bemyplan.service.payment.utils.AppleInAppPurchaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.deploy.bemyplan.common.exception.ErrorCode.CONFLICT_ORDER_PLAN;
import static com.deploy.bemyplan.common.exception.ErrorCode.NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Service
public class ApplePaymentService implements PaymentService {
    private final AppleInAppPurchaseValidator appleInAppPurchaseValidator;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    @Override
    public InAppPurchaseResponse purchaseValidate(Long orderId, UserReceiptDto userReceipt) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 주문 내역 입니다.", NOT_FOUND_EXCEPTION));

        AppStoreResponse response = appleInAppPurchaseValidator.appleInAppPurchaseVerify(userReceipt)
                .orElseThrow(() -> new NotFoundException("존재 하지 않는 응답입니다.", NOT_FOUND_EXCEPTION));

        String transactionId = getTransactionId(response);

        //앱이 비정상적으로 종료 되었는데 이미 DB에 TransactionId가 있는 경우 성공 처리 보내주기
        Optional<Payment> isPayment = paymentRepository.findByTransactionId(transactionId);
        if (isPayment.isPresent()){
            return InAppPurchaseResponse.of(isPayment.get().getPaymentState(), isPayment.get().getTransactionId());
        }

        Payment payment = Payment.of(
                order,
                getTransactionId(response),
                PaymentState.fromCode(response.getStatus())
        );

        paymentRepository.save(payment);

        return InAppPurchaseResponse.of(payment.getPaymentState(), payment.getTransactionId());
    }

    private String getTransactionId(AppStoreResponse response) {
        List<InApp> inAppData = response.getReceipt().getInApp();

        InApp app = inAppData.stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("트랜잭션 Id가 없습니다."));

        return app.getTransactionId();
    }
}
