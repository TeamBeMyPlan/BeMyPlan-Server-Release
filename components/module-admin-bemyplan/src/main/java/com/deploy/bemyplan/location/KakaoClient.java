package com.deploy.bemyplan.location;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao", url ="https://dapi.kakao.com")
public interface KakaoClient {

    @GetMapping("/v2/local/search/address.json")
    KakaoLocationModel getLocation(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("query") String query);
}
