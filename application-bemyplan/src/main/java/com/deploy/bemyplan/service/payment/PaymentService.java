package com.deploy.bemyplan.service.payment;

import com.deploy.bemyplan.service.payment.dto.request.UserReceiptDto;
import com.deploy.bemyplan.service.payment.dto.response.InAppPurchaseResponse;

public interface PaymentService {
    InAppPurchaseResponse purchaseValidate(Long orderId, UserReceiptDto userReceiptDto);
}
