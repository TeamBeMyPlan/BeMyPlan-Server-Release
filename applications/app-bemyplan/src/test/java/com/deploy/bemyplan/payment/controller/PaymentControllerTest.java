package com.deploy.bemyplan.payment.controller;

import com.deploy.bemyplan.payment.service.PaymentService;
import com.deploy.bemyplan.payment.service.dto.request.ConfirmOrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PaymentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PaymentService stubPaymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new PaymentController(stubPaymentService))
                .build();
    }

    @Test
    void confirmPurchaseReturnsHttpOkStatus() throws Exception {
        mockMvc.perform(post("/api/v1/payment/1/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"paymentId\":1,\"userId\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    void confirmPurchasePassesDtoToService() throws Exception {
        mockMvc.perform(post("/api/v1/payment/1/confirm")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"paymentId\":1,\"userId\":1}"));

        verify(stubPaymentService, times(1)).purchaseConfirm(1L, ConfirmOrderDto.of(1L, 1L));
    }
}