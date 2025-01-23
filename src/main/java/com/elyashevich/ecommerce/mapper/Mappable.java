package com.elyashevich.ecommerce.mapper;

import java.util.List;

public interface Mappable <E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<D> toDto(List<E> entities);
}