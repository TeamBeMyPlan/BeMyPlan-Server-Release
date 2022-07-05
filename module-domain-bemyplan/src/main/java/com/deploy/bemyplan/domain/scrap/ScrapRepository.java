package com.deploy.bemyplan.domain.scrap;

import com.deploy.bemyplan.domain.scrap.repository.ScrapRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long>, ScrapRepositoryCustom {
}
