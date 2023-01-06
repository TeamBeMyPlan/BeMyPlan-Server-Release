package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.plan.repository.PlanRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long>, PlanRepositoryCustom {
    @Query("SELECT p from Plan p where p.regionCategory = :region")
    List<Plan> findPlansByRegionAndSize(@Param("region") RegionCategory region, Pageable pageable);

    @Query("select p from Plan p where p.regionCategory = :region and p.status = 'ACTIVE' ORDER BY p.id desc ")
    List<Plan> findAllPlanByRegionCategory(@Param("region") RegionCategory regionCategory);

    @Query("select p from Plan p where p.status = 'ACTIVE' and p.rcmndStatus = 'RECOMMENDED' order by p.id desc")
    List<Plan> findPickList();

    @Query(value = "select p.id, p.title, p. thumbnail_url as thumbnailUrl, " +
            "p.created_at as createdAt, p.updated_at as updatedAt, " +
            "o.order_price as orderPrice " +
            "from plan p " +
            "inner join orders o on p.id = o.plan_id " +
            "where o.user_id = :userId and o.status = 'COMPLETED'",
            nativeQuery = true)
    List<OrderedPlan> findAllByOrderAndUserId(@Param("userId") Long userId);

    List<Plan> findAllByUserId(Long userId);
}
