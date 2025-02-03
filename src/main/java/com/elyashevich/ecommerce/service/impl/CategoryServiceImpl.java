package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Category;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.CategoryRepository;
import com.elyashevich.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    public static final String CATEGORY_WITH_NAME_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "Category with name '%s' not found";
    public static final String CATEGORY_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "Category with id '%s' not found";

    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAllPaginated(final int pageNo, final int pageSize, final String sortBy, final String sortDirection) {
        log.info("Finding paginated categories.");

        var sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        var pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        var result = categoryRepository.findAll(pageable);

        log.info("Found {} paginated categories.", result.getTotalElements());
        return result;
    }

    @Override
    public List<Category> findAll() {
        log.info("Finding all categories.");

        var categories = this.categoryRepository.findAll();

        log.info("Found {} categories.", categories.size());
        return categories;
    }

    @Override
    public Category findByName(final String name) {
        log.info("Finding a category with name '{}'.", name);

        var category = this.categoryRepository.findByName(name).orElseThrow(
                () -> {
                    var message = String.format(CATEGORY_WITH_NAME_WAS_NOT_FOUND_EXCEPTION_TEMPLATE, name);

                    log.warn(message);

                    return new ResourceNotFoundException(message);
                }
        );

        log.info("Found category with name '{}'.", category.getName());
        return category;
    }

    @Override
    public Category create(final Category category) {
        log.info("Creating category with name '{}'.", category.getName());

        var newCategory = this.categoryRepository.save(category);

        log.info("Created category with name '{}'.", newCategory.getName());
        return newCategory;
    }

    @Override
    public Category findById(final Long id) {
        log.info("Finding category with id '{}'.", id);

        var category = this.categoryRepository.findById(id).orElseThrow(
                () -> {
                    var message = String.format(CATEGORY_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE, id);

                    log.warn(message);

                    return new ResourceNotFoundException(message);
                }
        );

        log.info("Found category with id '{}'.", category.getId());
        return category;
    }

    @Transactional
    @Override
    public Category update(final Long id, final Category category) {
        var oldCategory = this.findById(id);

        log.info("Updating category with id '{}'.", oldCategory.getId());

        convert(oldCategory, category);
        var updatedCategory = this.categoryRepository.save(oldCategory);

        log.info("Updated category with id '{}'.", updatedCategory.getId());
        return updatedCategory;
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        var category = this.findById(id);

        log.info("Deleting category with id '{}'.", id);

        this.categoryRepository.delete(category);

        log.info("Deleted category with id '{}'.", id);
    }

    private static void convert(final Category oldCategory, final Category newCategory) {
        oldCategory.setName(newCategory.getName());
    }
}
