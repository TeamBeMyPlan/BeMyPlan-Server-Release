package com.deploy.bemyplan.payment.controller;

import com.deploy.bemyplan.common.controller.ResponseDTO;
import com.deploy.bemyplan.config.auth.Auth;
import com.deploy.bemyplan.config.auth.UserId;
import com.deploy.bemyplan.payment.controller.dto.request.ConfirmOrderRequest;
import com.deploy.bemyplan.payment.controller.dto.request.UserPurchaseReceipt;
import com.deploy.bemyplan.payment.service.PaymentService;
import com.deploy.bemyplan.payment.service.dto.response.InAppPurchaseResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @Auth
    public InAppPurchaseResponse validatePurchase(@UserId final Long userId, @PathVariable final Long orderId, @RequestBody final UserPurchaseReceipt userReceipt) {
        return paymentService.purchaseValidate(userId, orderId, userReceipt.toServiceDto());
    }

    @PostMapping("/{orderId}/confirm")
    public final ResponseDTO confirmPurchase(@PathVariable final Long orderId, @RequestBody final ConfirmOrderRequest request) {
        paymentService.purchaseConfirm(orderId, request.toServiceDto(request.getPaymentId(), request.getUserId()));
        return ResponseDTO.of("구매 확정 성공");
    }

    @DeleteMapping("/{orderId}/revert")
    @ApiOperation("[*주의 개발용] 구매 했던 내역을 되돌립니다.")
    public final ResponseDTO revertPurchase(@PathVariable final Long orderId) {
        paymentService.purchaseRevert(orderId);
        return ResponseDTO.of("구매 되돌리기 성공");
    }
}
