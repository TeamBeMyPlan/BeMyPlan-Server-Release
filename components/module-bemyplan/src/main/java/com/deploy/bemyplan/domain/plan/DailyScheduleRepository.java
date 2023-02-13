package com.deploy.bemyplan.domain.plan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
    List<DailySchedule> findAllByPlanId(Long planId);
}
