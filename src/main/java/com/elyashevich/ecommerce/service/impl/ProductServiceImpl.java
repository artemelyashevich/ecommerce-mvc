package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Product;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.ProductRepository;
import com.elyashevich.ecommerce.service.CategoryService;
import com.elyashevich.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    public static final String PRODUCT_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "Product with id '%s' not found";

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(final Long id) {
        return this.productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(PRODUCT_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE.formatted(id))
        );
    }

    @Transactional
    @Override
    public Product create(final Product product) {
        var category = this.categoryService.findByName(product.getCategory().getName());
        product.setCategory(category);
        return this.productRepository.save(product);
    }

    @Transactional
    @Override
    public Product update(final Long id, final Product product) {
        var oldProduct = this.findById(id);
        convert(oldProduct, product);
        return this.productRepository.save(oldProduct);
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        var product = this.findById(id);
        this.productRepository.delete(product);
    }

    private static void convert(final Product oldProduct, final Product newProduct) {
        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setCategory(newProduct.getCategory());
    }
}
