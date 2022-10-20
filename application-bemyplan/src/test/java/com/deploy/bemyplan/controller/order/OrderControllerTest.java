package com.deploy.bemyplan.controller.order;

import com.deploy.bemyplan.service.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest {
    private MockMvc mockMvc;

    @Mock
    private OrderService stubOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(stubOrderService))
                .build();
    }

    @Test
    void getOrderedPlanListReturnsOKHttpStatus() throws Exception {
        mockMvc.perform(get("/api/v1/order/plans"))
                .andExpect(status().isOk());
    }
}