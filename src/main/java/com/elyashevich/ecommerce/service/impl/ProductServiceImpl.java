package com.elyashevich.ecommerce.service.impl;

import com.elyashevich.ecommerce.entity.Product;
import com.elyashevich.ecommerce.exception.ResourceNotFoundException;
import com.elyashevich.ecommerce.repository.ProductRepository;
import com.elyashevich.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id '%s' not found".formatted(id))
        );
    }

    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Transactional
    @Override
    public Product update(Long id, Product product) {
        var oldProduct = this.findById(id);
        convert(oldProduct, product);
        return this.productRepository.save(oldProduct);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var product = this.findById(id);
        this.productRepository.delete(product);
    }

    private static void convert(Product oldProduct, Product newProduct) {
        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setCategory(newProduct.getCategory());
    }
}
