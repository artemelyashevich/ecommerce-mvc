package com.elyashevich.ecommerce.mapper;

import com.elyashevich.ecommerce.dto.UserDto;
import com.elyashevich.ecommerce.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
