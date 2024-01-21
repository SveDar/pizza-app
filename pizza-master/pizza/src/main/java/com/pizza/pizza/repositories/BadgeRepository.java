package com.pizza.pizza.repositories;

import com.pizza.pizza.entities.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
    boolean existsById(Long id);
}
