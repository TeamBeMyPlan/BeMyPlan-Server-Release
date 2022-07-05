package com.deploy.bemyplan.service.scrap;

import com.deploy.bemyplan.domain.scrap.Scrap;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrapServiceUtils {

    public static Scrap findScrapByPlanIdAndUserId(ScrapRepository scrapRepository, Long planId, Long userId) {
        Scrap scrap = scrapRepository.findByUserIdAndPlanId(planId, userId);
        if (scrap == null) {
            return scrapRepository.save(Scrap.of(planId, userId));
        }
        return scrap;
    }
}
