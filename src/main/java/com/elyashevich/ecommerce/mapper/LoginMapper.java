package com.elyashevich.ecommerce.mapper;

import com.elyashevich.ecommerce.dto.auth.LoginDto;
import com.elyashevich.ecommerce.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper extends Mappable<User, LoginDto> {
}
