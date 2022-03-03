package com.deploy.bemyplan.service.scrap;

import com.deploy.bemyplan.domain.scrap.Scrap;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import com.deploy.bemyplan.domain.scrap.ScrapStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;

    @Transactional
    public void CreateOrAddScrap(Long planId, Long userId){
        Scrap scrap = scrapRepository.findByUserIdAndPlanId(planId, userId);
        if (scrap.getStatus() == ScrapStatus.INACTIVE){
            scrap.updateToActive();
        } else {
            scrapRepository.save(Scrap.builder().build());
        }
    }

    @Transactional
    public void deleteScrap(Long userId, Long planId){
        Scrap scrap = scrapRepository.findByUserIdAndPlanId(userId, planId);
        scrap.updateToInActive();
    }


}

