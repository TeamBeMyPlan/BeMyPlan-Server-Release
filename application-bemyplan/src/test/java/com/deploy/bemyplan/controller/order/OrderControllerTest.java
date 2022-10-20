package com.deploy.bemyplan.controller.order;

import com.deploy.bemyplan.domain.plan.OrderedPlan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.service.order.OrderService;
import com.deploy.bemyplan.service.order.dto.response.OrderListResponse;
import com.deploy.bemyplan.service.order.dto.response.OrderedPlanInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Test
    void getOrderPlanListReturnsResponse() throws Exception {
        OrderedPlan givenPlan = OrderedPlan.newInstance(1L, RegionCategory.JEJU, Region.JEJUALL, "thumbnailUrl", "title", "description", TagInfo.testBuilder().build(), 10000, 100, 4000, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList(), 4400);
        OrderListResponse response = OrderListResponse.of(List.of(OrderedPlanInfoResponse.of(givenPlan)));
        given(stubOrderService.getOrderedPlanList(any())).willReturn(response);

        mockMvc.perform(get("/api/v1/order/plans"))
                .andDo(print())
                .andExpect(jsonPath("$.contents[0].planId", equalTo(response.getContents().get(0).getPlanId())))
                .andExpect(jsonPath("$.contents[0].orderPrice", equalTo(response.getContents().get(0).getOrderPrice())))
                .andExpect(jsonPath("$.contents[0].title", equalTo(response.getContents().get(0).getTitle())))
                .andExpect(jsonPath("$.contents[0].thumbnailUrl", equalTo(response.getContents().get(0).getThumbnailUrl())));
    }
}