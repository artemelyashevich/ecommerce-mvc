package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Product;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.ProductRepository;
import com.elyashevich.ecommerce.service.CategoryService;
import com.elyashevich.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    public static final String PRODUCT_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE = "Product with id '%s' not found";

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public List<Product> findAll() {
        log.info("Find all products");

        var products = this.productRepository.findAll();

        log.info("Found {} products", products.size());
        return products;
    }

    @Override
    public Product findById(final Long id) {
        log.info("Find product by id: {}", id);

        var product = this.productRepository.findById(id).orElseThrow(
                () -> {
                    var message = String.format(PRODUCT_WITH_ID_WAS_NOT_FOUND_EXCEPTION_TEMPLATE, id);

                    log.warn(message);

                    return new ResourceNotFoundException(message);
                }
        );

        log.info("Found product with id '{}'", product.getId());
        return product;
    }

    @Transactional
    @Override
    public Product create(final Product product) {
        log.info("Create product: {}", product);

        var category = this.categoryService.findByName(product.getCategory().getName());
        product.setCategory(category);

        var newCategory = this.productRepository.save(product);

        log.info("Created product: {}", product);
        return newCategory;
    }

    @Transactional
    @Override
    public Product update(final Long id, final Product product) {
        log.info("Update product: {}", product);

        var oldProduct = this.findById(id);
        convert(oldProduct, product);

        var updatedProduct = this.productRepository.save(oldProduct);

        log.info("Updated product: {}", product);

        return updatedProduct;
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        log.info("Delete product: {}", id);

        var product = this.findById(id);
        this.productRepository.delete(product);

        log.info("Deleted product: {}", id);
    }

    private static void convert(final Product oldProduct, final Product newProduct) {
        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setCategory(newProduct.getCategory());
    }
}
