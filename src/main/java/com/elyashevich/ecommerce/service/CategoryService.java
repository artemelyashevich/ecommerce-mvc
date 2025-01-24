package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id);

    Category findByName(String name);

    Category create(Category category);

    Category update(Long id, Category category);

    void delete(Long id);
}
