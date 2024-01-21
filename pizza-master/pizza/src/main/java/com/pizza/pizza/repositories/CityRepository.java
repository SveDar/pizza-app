package com.pizza.pizza.repositories;

import com.pizza.pizza.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
   City findCityByName(String name);
}
