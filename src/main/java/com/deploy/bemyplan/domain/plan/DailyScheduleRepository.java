package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.plan.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
}
