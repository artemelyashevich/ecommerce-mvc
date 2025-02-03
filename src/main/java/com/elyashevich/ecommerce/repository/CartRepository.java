package com.elyashevich.ecommerce.repository;

import com.elyashevich.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    void deleteAllByUserId(final Long userId);
    List<Cart> findAllByUserId(final Long id);
}
