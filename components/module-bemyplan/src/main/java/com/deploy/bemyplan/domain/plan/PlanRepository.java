package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.plan.repository.PlanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long>, PlanRepositoryCustom {
    @Query(value = "SELECT * from plan p where p.region_category = :region AND p.id <> :planId ORDER BY RAND() LIMIT :size",
            nativeQuery = true)
    List<Plan> findPlansByRegionAndSize(@Param("planId") Long planId, @Param("region") String region, @Param("size") int size);

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

    List<Plan> findAllByCreatorId(Long creatorId);
}
