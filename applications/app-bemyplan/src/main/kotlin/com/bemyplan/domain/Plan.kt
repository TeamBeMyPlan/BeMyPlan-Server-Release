package com.bemyplan.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Plan {
    private Long id;
    private Long creatorId;
    private Place place;
    private String thumbnailUrl;
    private String title;
    private String description;
    private Theme theme;
    private TravelPartner partner;
    private TravelMobility mobility;
    private BigDecimal amount;
    private int month;
    private int price;
    private boolean recommend;
    private List<String> hashtags = new ArrayList<>();
    private List<String> recommendTargets = new ArrayList<>();
    private List<Spot> spots = new ArrayList<>();
}
