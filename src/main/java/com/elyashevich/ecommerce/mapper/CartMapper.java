package com.elyashevich.ecommerce.mapper;

import com.elyashevich.ecommerce.dto.CartDto;
import com.elyashevich.ecommerce.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper extends Mappable<Cart, CartDto> {
}
