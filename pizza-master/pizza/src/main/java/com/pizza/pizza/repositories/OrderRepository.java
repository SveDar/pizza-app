package com.pizza.pizza.repositories;

import com.pizza.pizza.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public Order findOrderById(Long id);
    public List<Order> findOrdersByCustomer_PhoneNumber(String phoneNumber);


    public Set<Order> findOrdersByFinished(boolean finished);
}
