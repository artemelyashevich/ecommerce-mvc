package com.elyashevich.ecommerce.mapper;

import com.elyashevich.ecommerce.dto.CategoryDto;
import com.elyashevich.ecommerce.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends Mappable<Category, CategoryDto> {
}
