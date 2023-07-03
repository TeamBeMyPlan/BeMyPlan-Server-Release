package com.deploy.bemyplan.plan.domain

import java.math.BigDecimal

class Plan (
    val id: Long,
    val creatorId: Long,
    val place: Place,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val theme: Theme,
    val partner: TravelPartner,
    val mobility: TravelMobility,
    val amount: BigDecimal,
    val month: Int,
    val price: Int,
    val recommend: Boolean,
    val hashtags: List<String>,
    val recommendTargets: List<String>,
    val spots: List<Spot>,
)