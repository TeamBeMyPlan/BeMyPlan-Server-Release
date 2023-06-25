package com.bemyplan.plan.domain

class Spot(
    val id: Long,
    val title: String,
    val category: SpotCategory,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val tip: String,
    val review: String,
    val images: List<SpotImage>,
    val day: Int,
    val vehicle: TravelMobility,
    val spentMinute: Int,
)