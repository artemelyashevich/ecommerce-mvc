package com.elyashevich.ecommerce.mapper;

import com.elyashevich.ecommerce.dto.OrderDto;
import com.elyashevich.ecommerce.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends Mappable<Order, OrderDto> {
}
