package com.deploy.bemyplan.controller.payment;

import com.deploy.bemyplan.controller.common.response.ResponseDTO;
import com.deploy.bemyplan.controller.payment.dto.request.ConfirmOrderRequest;
import com.deploy.bemyplan.controller.payment.dto.request.UserPurchaseReceipt;
import com.deploy.bemyplan.service.payment.PaymentService;
import com.deploy.bemyplan.service.payment.dto.response.InAppPurchaseResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/{orderId}/verify")
    public InAppPurchaseResponse validatePurchase(@PathVariable final Long orderId, @RequestBody final UserPurchaseReceipt userReceipt) {
        return paymentService.purchaseValidate(orderId, userReceipt.toServiceDto());
    }

    @PostMapping("/{orderId}/confirm")
    public final ResponseDTO confirmPurchase(@PathVariable final Long orderId, @RequestBody final ConfirmOrderRequest request) {
        paymentService.purchaseConfirm(orderId, request.toServiceDto(request.getPaymentId(), request.getUserId()));
        return ResponseDTO.of("구매 확정 성공");
    }
}
