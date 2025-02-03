package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The CategoryService interface provides methods for managing categories in the e-commerce system.
 */
public interface CategoryService {

    /**
     * Finds all categories with pagination and sorting options.
     *
     * @param pageNo the page number to retrieve
     * @param pageSize the number of categories per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction to sort (ascending or descending)
     * @return a paginated list of categories
     */
    Page<Category> findAllPaginated(final int pageNo, final int pageSize, final String sortBy, final String sortDirection);

    /**
     * Finds all categories.
     *
     * @return a list of all categories
     */
    List<Category> findAll();

    /**
     * Finds a category by its ID.
     *
     * @param id the ID of the category to find
     * @return the found category, or null if not found
     */
    Category findById(final Long id);

    /**
     * Finds a category by its name.
     *
     * @param name the name of the category to find
     * @return the found category, or null if not found
     */
    Category findByName(final String name);

    /**
     * Creates a new category.
     *
     * @param category the category to create
     * @return the created category
     */
    Category create(final Category category);

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to update
     * @param category the updated category information
     * @return the updated category
     */
    Category update(final Long id, final Category category);

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete
     */
    void delete(final Long id);
}
