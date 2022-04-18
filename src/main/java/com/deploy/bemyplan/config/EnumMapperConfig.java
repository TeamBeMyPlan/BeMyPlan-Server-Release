package com.deploy.bemyplan.config;

import com.deploy.bemyplan.common.util.EnumMapper;
import com.deploy.bemyplan.domain.plan.*;
import com.deploy.bemyplan.domain.user.UserSocialType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnumMapperConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();

        //user
        enumMapper.put("UserSocialType", UserSocialType.class);

        //plan
        enumMapper.put("TravelTheme", TravelTheme.class);
        enumMapper.put("TravelPartner", TravelPartner.class);
        enumMapper.put("TravelMobility", TravelMobility.class);

        //region
        enumMapper.put("RegionType", RegionType.class);

        //spot
        enumMapper.put("SpotCategoryType", SpotCategoryType.class);

        return enumMapper;
    }
}
