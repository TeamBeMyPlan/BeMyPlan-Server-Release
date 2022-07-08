package com.deploy.bemyplan.controller.payment;

import com.deploy.bemyplan.service.payment.PaymentService;
import com.deploy.bemyplan.service.payment.PurchaseReceipt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}/verify")
    public boolean validatePurchase(@PathVariable Long orderId, @RequestBody PurchaseReceipt receipt) {
        return paymentService.validate(orderId, receipt);
    }
}
