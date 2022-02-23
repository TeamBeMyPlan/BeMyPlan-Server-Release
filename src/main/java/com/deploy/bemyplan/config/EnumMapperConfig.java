package com.deploy.bemyplan.config;

import com.deploy.bemyplan.common.util.EnumMapper;
import com.deploy.bemyplan.domain.post.*;
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

        //post
        enumMapper.put("TravelTheme", TravelTheme.class);
        enumMapper.put("TravelPartner", TravelPartner.class);
        enumMapper.put("TravelMobility", TravelMobility.class);

        //spot
        enumMapper.put("SpotCategoryType", SpotCategoryType.class);
        enumMapper.put("SpotContentType", SpotContentType.class);

        return enumMapper;
    }
}
