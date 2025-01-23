package com.elyashevich.ecommerce.repository;

import com.elyashevich.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
