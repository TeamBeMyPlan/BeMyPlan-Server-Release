package com.deploy.bemyplan.service.payment.impl;

import com.deploy.bemyplan.common.exception.model.ValidationException;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.order.OrderStatus;
import com.deploy.bemyplan.domain.payment.Payment;
import com.deploy.bemyplan.domain.payment.PaymentRepository;
import com.deploy.bemyplan.domain.payment.PaymentState;
import com.deploy.bemyplan.service.payment.dto.request.ConfirmOrderDto;
import com.deploy.bemyplan.service.payment.utils.AppleInAppPurchaseValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ApplePaymentServiceTest {
    private ApplePaymentService paymentService;
    private AppleInAppPurchaseValidator spyAppleInAppPurchaseValidator;
    private OrderRepository spyOrderRepository;
    private PaymentRepository spyPaymentRepository;

    @BeforeEach
    void setUp() {
        spyAppleInAppPurchaseValidator = mock(AppleInAppPurchaseValidator.class);
        spyOrderRepository = mock(OrderRepository.class);
        spyPaymentRepository = mock(PaymentRepository.class);

        paymentService = new ApplePaymentService(spyAppleInAppPurchaseValidator, spyOrderRepository, spyPaymentRepository);
    }

    @Test
    void purchaseConfirm_callsOrderFromRepository() {
        Order givenOrder = Order.of(1L, 1L, OrderStatus.COMPLETED, 1000);
        Payment givenPayment = Payment.of(givenOrder, "1111", PaymentState.COMPLETE);
        given(spyOrderRepository.findById(givenOrder.getId())).willReturn(Optional.of(givenOrder));
        given(spyPaymentRepository.findById(any())).willReturn(Optional.of(givenPayment));

        paymentService.purchaseConfirm(givenOrder.getId(), ConfirmOrderDto.of(1L, 1L));

        verify(spyOrderRepository, times(1)).findById(givenOrder.getId());
    }

    @Test
    void purchaseConfirm_callsPaymentFromRepository() {
        final Order givenOrder = Order.of(1L, 1L, OrderStatus.COMPLETED, 1000);
        final Payment givenPayment = Payment.of(givenOrder, "1111", PaymentState.COMPLETE);
        given(spyOrderRepository.findById(any())).willReturn(Optional.of(givenOrder));
        given(spyPaymentRepository.findById(givenPayment.getId())).willReturn(Optional.of(givenPayment));

        paymentService.purchaseConfirm(givenOrder.getId(), ConfirmOrderDto.of(givenPayment.getId(), 1L));

        verify(spyPaymentRepository, times(1)).findById(givenPayment.getId());
    }

    @Test
    void purchaseConfirm_changesStatus() {
        final Order givenOrder = Order.of(1L, 1L, OrderStatus.UNPAID, 1000);
        final Payment givenPayment = Payment.of(givenOrder, "1111", PaymentState.COMPLETE);
        given(spyOrderRepository.findById(givenOrder.getId())).willReturn(Optional.of(givenOrder));
        given(spyPaymentRepository.findById(givenPayment.getId())).willReturn(Optional.of(givenPayment));

        paymentService.purchaseConfirm(givenOrder.getId(), ConfirmOrderDto.of(givenPayment.getId(), 1L));

        Assertions.assertThat(givenOrder.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @Test
    void purchaseConfirm_returnsValidationExceptionWhenOrderIdIsNotEqual() {
        final Order givenOrder = Order.of(1L, 1L, OrderStatus.UNPAID, 1000);
        final Order anotherOrder = Order.of(2L, 1L, OrderStatus.UNPAID, 2000);
        final Payment givenPayment = Payment.of(anotherOrder, "1111", PaymentState.COMPLETE);

        given(spyOrderRepository.findById(any())).willReturn(Optional.of(givenOrder));
        given(spyPaymentRepository.findById(givenPayment.getId())).willReturn(Optional.of(givenPayment));

        assertThatThrownBy(() -> paymentService.purchaseConfirm(1L, ConfirmOrderDto.of(givenPayment.getId(), 1L)))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void purchaseConfirm_returnsValidationExceptionWhenPaymentIsNotCompleted() {
        final Order givenOrder = Order.of(1L, 1L, OrderStatus.UNPAID, 1000);
        final Payment givenPayment = Payment.of(givenOrder, "1111", PaymentState.FAIL);

        given(spyOrderRepository.findById(any())).willReturn(Optional.of(givenOrder));
        given(spyPaymentRepository.findById(givenPayment.getId())).willReturn(Optional.of(givenPayment));

        assertThatThrownBy(() -> paymentService.purchaseConfirm(givenOrder.getId(), ConfirmOrderDto.of(givenPayment.getId(), 1L)))
                .isInstanceOf(ValidationException.class);

    }
}