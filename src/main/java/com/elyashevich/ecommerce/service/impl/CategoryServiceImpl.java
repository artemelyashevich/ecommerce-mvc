package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Category;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.CategoryRepository;
import com.elyashevich.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findByName(final String name) {
        return this.categoryRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Category with name '%s' not found".formatted(name))
        );
    }

    @Override
    public Category create(final Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category findById(final Long id) {
        return this.categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category with id '%s' not found".formatted(id))
        );
    }

    @Transactional
    @Override
    public Category update(final Long id, final Category category) {
        var oldCategory = this.findById(id);
        convert(oldCategory, category);
        return this.categoryRepository.save(oldCategory);
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        var category = this.findById(id);
        this.categoryRepository.delete(category);
    }

    private static void convert(final Category oldCategory, final Category newCategory) {
        oldCategory.setName(newCategory.getName());
    }
}
