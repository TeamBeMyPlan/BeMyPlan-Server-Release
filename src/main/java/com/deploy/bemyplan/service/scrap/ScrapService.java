package com.deploy.bemyplan.service.scrap;

import com.deploy.bemyplan.domain.scrap.Scrap;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import com.deploy.bemyplan.domain.scrap.ScrapStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;

    @Transactional
    public Boolean CreateOrAddScrap(Long userId, Long planId){
        scrapRepository.findByUserIdAndPlanId(userId, planId)
                .ifPresentOrElse(scrap-> scrap.updateToActive(),
                        () -> {
                            scrapRepository.save(new Scrap(userId, planId, ScrapStatus.ACTIVE));
                        });
        return true;
    }

    @Transactional
    public void deleteScrap(Long userId, Long planId){
        Optional<Scrap> scrap = scrapRepository.findByUserIdAndPlanId(userId, planId);
        if (scrap.get().getStatus() == ScrapStatus.INACTIVE){
            scrap.get().updateToActive();
        };
    }


}

