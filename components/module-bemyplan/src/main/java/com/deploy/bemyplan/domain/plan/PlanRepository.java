package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.plan.repository.PlanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long>, PlanRepositoryCustom {
    @Query(value = "SELECT * from plan p " +
            "WHERE p.region_category = :region AND p.id <> :planId AND p.status = 'ACTIVE' " +
            "ORDER BY RAND() LIMIT :size",
            nativeQuery = true)
    List<Plan> findPlansByRegionAndSize(@Param("planId") Long planId, @Param("region") String region, @Param("size") int size);

    @Query("select p from Plan p where p.regionCategory = :region and p.status = 'ACTIVE' ORDER BY p.id desc ")
    List<Plan> findAllPlanByRegionCategory(@Param("region") RegionCategory regionCategory);

    @Query(value = "SELECT p.* FROM plan p\n" +
            "LEFT JOIN scrap s ON p.id = s.plan_id WHERE region_category = 'JEJU' AND p.status = 'ACTIVE'\n" +
            "GROUP BY p.id\n" +
            "ORDER BY COUNT(s.id) DESC;", nativeQuery = true)
    List<Plan> findAllPlanByRegionCategoryOrderByScrap(@Param("region") RegionCategory regionCategory);

    @Query("select p from Plan p where p.status = 'ACTIVE' and p.rcmndStatus = 'RECOMMENDED' order by p.id desc")
    List<Plan> findPickList();

    @Query(value = "select p.id, p.title, p. thumbnail_url as thumbnailUrl, " +
            "p.created_at as createdAt, p.updated_at as updatedAt, " +
            "o.order_price as orderPrice " +
            "from plan p " +
            "inner join orders o on p.id = o.plan_id " +
            "where o.user_id = :userId and o.status = 'COMPLETED' order by o.created_at desc",
            nativeQuery = true)
    List<OrderedPlan> findAllByOrderAndUserId(@Param("userId") Long userId);

    List<Plan> findAllByCreatorId(Long creatorId);

    @Query(value = "select p.id, p.title, p.thumbnail_url as thumbnailUrl, s2.created_at as createdAt, (select count(*) FROM scrap s WHERE s.plan_id = p.id) as scp_count from plan p " +
            "inner join scrap s2 on p.id = s2.plan_id where s2.user_id = :userId order by scp_count desc", nativeQuery = true)
    List<ScrapedPlan> findScrapPlanOrderByScrapCount(@Param("userId") Long userId);

    @Query(value = "select p.id, p.title, p.thumbnail_url as thumbnailUrl, s.created_at as createdAt from plan p inner join scrap s on p.id = s.plan_id where s.user_id = :userId " +
            "order by s.created_at desc", nativeQuery = true)
    List<ScrapedPlan> findScrapPlanOrderByCreatedAtDesc(@Param("userId") Long userId);

    @Query(value = "select p.id, p.title, p.thumbnail_url as thumbnailUrl, s.created_at as createdAt from plan p inner join scrap s on p.id = s.plan_id where s.user_id = :userId " +
            "order by p.order_cnt desc", nativeQuery = true)
    List<ScrapedPlan> findScrapPlanOrderByOrderCount(@Param("userId") Long userId);

    @Query(value = "select * from plan p order by order_cnt desc limit 10", nativeQuery = true)
    List<Plan> findAllByOrderCntDesc();

    @Query(value = "select * from plan p order by created_at desc limit 10", nativeQuery = true)
    List<Plan> findAllByCreatedAtDesc();

    @Query(value = "select * from plan p inner join creator c on p.user_id = c.id where p.title like %:keyword% or c.name like %:keyword%", nativeQuery = true)
    List<Plan> findBySearchKeyword(@Param("keyword") String keyword);

}
