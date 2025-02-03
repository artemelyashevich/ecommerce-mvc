package com.elyashevich.ecommerce.service;

import com.elyashevich.ecommerce.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    Page<Category> findAllPaginated(final int pageNo, final int pageSize, final String sortBy, final String sortDirection);

    List<Category> findAll();

    Category findById(final Long id);

    Category findByName(final String name);

    Category create(final Category category);

    Category update(final Long id, final Category category);

    void delete(final Long id);
}
