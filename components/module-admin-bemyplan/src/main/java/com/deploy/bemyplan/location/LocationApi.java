package com.deploy.bemyplan.location;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class LocationApi {

    private final LocationService locationService;

    @GetMapping("location")
    public Location getLocation(@RequestParam String query) {
        return locationService.getLocation(query);
    }
}
