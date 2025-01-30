package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(final Long id);

    Category findByName(final String name);

    Category create(final Category category);

    Category update(final Long id, final Category category);

    void delete(final Long id);
}
