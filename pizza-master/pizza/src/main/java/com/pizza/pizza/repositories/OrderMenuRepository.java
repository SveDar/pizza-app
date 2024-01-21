package com.pizza.pizza.repositories;

import com.pizza.pizza.entities.OrderMenu;
import com.pizza.pizza.entities.keys.OrderMenuKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, OrderMenuKey> {
}
