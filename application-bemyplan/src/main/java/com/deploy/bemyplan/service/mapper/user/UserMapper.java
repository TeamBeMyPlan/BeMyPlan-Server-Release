package com.deploy.bemyplan.service.mapper.user;

import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.service.user.dto.request.CreateUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User createUserDtoToEntity(CreateUserDto dto);
}
