package com.deploy.bemyplan.payment.service;

import com.deploy.bemyplan.payment.service.dto.request.ConfirmOrderDto;
import com.deploy.bemyplan.payment.service.dto.request.UserReceiptDto;
import com.deploy.bemyplan.payment.service.dto.response.InAppPurchaseResponse;

public interface PaymentService {
    InAppPurchaseResponse purchaseValidate(Long orderId, UserReceiptDto userReceiptDto);

    void purchaseConfirm(Long orderId, ConfirmOrderDto confirmOrderDto);
    void purchaseRevert(Long orderId);
}
