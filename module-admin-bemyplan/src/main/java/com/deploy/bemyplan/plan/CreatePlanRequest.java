package com.deploy.bemyplan.plan;

import lombok.Getter;

import java.util.List;

@Getter
public class CreatePlanRequest {
    private Creator creator;
    private Plan plan;
    private List<Spot> spots;
    private List<Preview> previews;

    @Getter
    public static class Creator {
        private String name;
    }

    @Getter
    public static class Plan {
        private String title;
        private String description;
        private int price;
        private boolean recommend;
        private String vehicle;
        private String concept;
        private int cost;
        private int period;
        private String partner;
    }

    @Getter
    public static class Spot {
        private int id;
        private String address;
        private int date;
        private boolean hasNext;
        private double latitude;
        private double longitude;
        private String name;
        private NextSpot nextSpot;
        private String review;
        private List<String> savedImages;
        private String tip;

        @Getter
        public static class NextSpot {
            private int id;
            private String vehicle;
            private int spentTime;
        }
    }

    @Getter
    public static class Preview {
        private String description;
        private String image;
    }
}
