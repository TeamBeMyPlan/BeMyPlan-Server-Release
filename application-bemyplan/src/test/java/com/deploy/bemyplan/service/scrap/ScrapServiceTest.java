package com.deploy.bemyplan.service.scrap;

import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ScrapServiceTest {
    private ScrapService scrapService;
    private ScrapRepository spyScrapRepository;

    @BeforeEach
    void setUp() {
        spyScrapRepository = mock(ScrapRepository.class);
        scrapService = new ScrapService(spyScrapRepository);
    }

    @Test
    @DisplayName("모든 스크랩 삭제 repository 호출 ")
    void deleteAllScrapByUser() {
        scrapService.deleteAllScrapByUser(1L);
        verify(spyScrapRepository, times(1)).deleteByUserId(1L);
    }

}