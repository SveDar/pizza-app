package com.pizza.pizza.repositories;

import com.pizza.pizza.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {   //za generirane na sql zaqvki bez izpolzwane na sql(primerno jpql)

    Menu findMenuByItem(String name);
}
