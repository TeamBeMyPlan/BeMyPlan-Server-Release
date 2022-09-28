package com.deploy.bemyplan.service.payment;

import com.deploy.bemyplan.service.payment.dto.request.ConfirmOrderDto;
import com.deploy.bemyplan.service.payment.dto.request.UserReceiptDto;
import com.deploy.bemyplan.service.payment.dto.response.InAppPurchaseResponse;

public interface PaymentService {
    InAppPurchaseResponse purchaseValidate(Long orderId, UserReceiptDto userReceiptDto);

    void purchaseConfirm(Long orderId, ConfirmOrderDto confirmOrderDto);
}
