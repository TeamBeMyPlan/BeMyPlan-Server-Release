package com.deploy.bemyplan.service.scrap;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.scrap.Scrap;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.deploy.bemyplan.common.exception.ErrorCode.NOT_FOUND_SCRAP_EXCEPTION;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;

    @Transactional
    public void addScrap(Long planId, Long userId) {
        scrapRepository.save(Scrap.of(planId, userId));
    }

    @Transactional
    public void deleteScrap(Long userId, Long planId){
        scrapRepository.deleteByUserIdAndPlanId(userId, planId);
    }

    @Transactional(readOnly = true)
    public void checkScrapStatus(Long planId, Long userId) {
        scrapRepository.findByUserIdAndPlanId(planId, userId)
                .orElseThrow(() -> new NotFoundException(String.format("해당 여행일정을 찜한 상태 (%s - %s) 가 아닙니다.", userId, planId), NOT_FOUND_SCRAP_EXCEPTION));
    }
}

