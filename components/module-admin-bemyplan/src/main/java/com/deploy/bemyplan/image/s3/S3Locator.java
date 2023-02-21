package com.deploy.bemyplan.image.s3;

public class S3Locator {

    private static final String S3_URL = "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/";

    public static String get(String path) {
        if (path.startsWith("http")) {
            return path;
        }

        return S3_URL + path;
    }
}
