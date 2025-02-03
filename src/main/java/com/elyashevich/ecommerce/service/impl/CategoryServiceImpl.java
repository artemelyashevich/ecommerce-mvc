package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Category;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.CategoryRepository;
import com.elyashevich.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    public static final String CATEGORY_WITH_NAME_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "Category with name '%s' not found";
    public static final String CATEGORY_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "Category with id '%s' not found";

    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAllPaginated(final int pageNo, final int pageSize, final String sortBy, final String sortDirection) {
        var sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        var pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        System.out.println(this.categoryRepository.findAll(pageable));
        return this.categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findByName(final String name) {
        return this.categoryRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException(CATEGORY_WITH_NAME_WAS_NOT_FOUND_EXCEPTION_TEMPLATE.formatted(name))
        );
    }

    @Override
    public Category create(final Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category findById(final Long id) {
        return this.categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(CATEGORY_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE.formatted(id))
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
