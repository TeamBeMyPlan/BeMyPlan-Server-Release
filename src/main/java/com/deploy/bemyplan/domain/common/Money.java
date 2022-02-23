package com.deploy.bemyplan.domain.common;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Money {

    public static final Money ZERO = Money.wons(0);

    @Column(nullable = false)
    private BigDecimal amount;

    private Money(BigDecimal amount) {
//        validateMoneyRange(amount);
        this.amount = amount;
    }

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

//    private void validateMoneyRange(BigDecimal money) {
//        if (money.compareTo(BigDecimal.ZERO) < 0) {
//            throw new ValidationException(String.format("잘못된 Money 값입니다. (%s)", money), VALIDATION_MONEY_EXCEPTION);
//        }
//    }

//    public Money times(double percent) {
//        return new Money(this.amount.muliply(
//                BigDecimal.valueOf(percent)));
//    }

//    public boolean isLessThan(Money other) {
//        return amount.compareTo(other.amount) < 0;
//    }
//
//    public boolean isGreaterThanOrEqual(Money other) {
//        return amount.compareTo(other.amount) >= 0;
//    }
}