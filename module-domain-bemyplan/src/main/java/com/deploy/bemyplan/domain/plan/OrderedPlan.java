package com.deploy.bemyplan.domain.plan;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderedPlan extends Plan {
    private int orderPrice;

    private OrderedPlan(final Long userId, final RegionCategory regionCategory, final Region region, final String thumbnailUrl, final String title, final String description, final TagInfo tagInfo, final int orderCnt, final int viewCnt, final int price, final PlanStatus status, final RcmndStatus rcmndStatus, final List<String> hashtags, final List<String> recommendTargets, final int orderPrice) {
        super(userId, regionCategory, region, thumbnailUrl, title, description, tagInfo, orderCnt, viewCnt, price, status, rcmndStatus, hashtags, recommendTargets);
        this.orderPrice = orderPrice;
    }

    public static OrderedPlan newInstance(final Long userId, final RegionCategory regionCategory, final Region region, final String thumbnailUrl, final String title, final String description, final TagInfo tagInfo, final int orderCnt, final int viewCnt, final int price, final PlanStatus status, final RcmndStatus rcmndStatus, final List<String> hashtags, final List<String> recommendTargets, final int orderPrice) {
        return new OrderedPlan(userId, regionCategory, region, thumbnailUrl, title, description, tagInfo, orderCnt, viewCnt, price, status, rcmndStatus, hashtags, recommendTargets, orderPrice);
    }
}
