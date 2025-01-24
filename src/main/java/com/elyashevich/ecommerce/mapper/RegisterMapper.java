package com.elyashevich.ecommerce.mapper;

import com.elyashevich.ecommerce.dto.auth.RegisterDto;
import com.elyashevich.ecommerce.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper extends Mappable<User, RegisterDto> {
}
