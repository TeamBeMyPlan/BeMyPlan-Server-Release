package com.deploy.bemyplan.controller.payment;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.controller.payment.dto.request.UserPurchaseReceipt;
import com.deploy.bemyplan.service.payment.PaymentService;
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
    public ApiResponse validatePurchase(@PathVariable Long orderId, @RequestBody UserPurchaseReceipt userReceipt){
        return ApiResponse.success(paymentService.purchaseValidate(orderId, userReceipt.toServiceDto()));
    }
}
