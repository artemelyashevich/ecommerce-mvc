package com.elyashevich.ecommerce.mapper;

import com.elyashevich.ecommerce.dto.ProductDto;
import com.elyashevich.ecommerce.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Mappable<Product, ProductDto> {
}
