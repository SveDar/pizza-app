package com.pizza.pizza.repositories;

import com.pizza.pizza.entities.City;
import com.pizza.pizza.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findCustomerByFirstNameAndLastName(String firstName, String lastName);

    @Query("Select c "+
            "FROM Customer c "+
            "WHERE "+
            "c.city.name "+
            "LIKE :#{#cityName ==null || #cityName.isEmpty()? '%': #cityName+'%'} ")
    Set<Customer> findCustomersByCity(String cityName);

    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);
}
