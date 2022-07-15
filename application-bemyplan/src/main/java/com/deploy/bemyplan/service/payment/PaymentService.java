package com.deploy.bemyplan.service.payment;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.payment.Payment;
import com.deploy.bemyplan.domain.payment.PaymentRepository;
import com.deploy.bemyplan.domain.payment.PaymentState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.deploy.bemyplan.common.exception.ErrorCode.NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;
    private final PaymentRepository paymentRepository;

    @Transactional
    public boolean validate(final Long orderId, final PurchaseReceipt receipt) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("NOT FOUND EXCEPTION", NOT_FOUND_EXCEPTION));

        final ReceiptValidateResult result = requestPurchaseValidate(receipt);
        final Payment payment = Payment.of(order,
                result.getTransactionId(),
                PaymentState.fromCode(result.getStatus()));

        payment.validate();

        paymentRepository.save(payment);

        return result.isSuccess();
    }

    private ReceiptValidateResult requestPurchaseValidate(final PurchaseReceipt receipt) {
        return paymentClient.validate(receipt);
    }

}