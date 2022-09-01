package com.deploy.bemyplan.domain.payment;

import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    @Override
    public Payment save(Payment payment) {
        return payment;
    }
}
