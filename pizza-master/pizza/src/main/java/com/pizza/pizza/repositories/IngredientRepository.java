package com.pizza.pizza.repositories;

import com.pizza.pizza.entities.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    Ingredient findIngredientByName(String name);

    @Query("Select i "+
            "FROM Ingredient i ")
    Page<Ingredient> findAllIngredients(Pageable pageable);
}
