package com.threetopia.menu.repo;

import com.threetopia.menu.entity.Ingredient;
import com.threetopia.menu.entity.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Ingredient findByName(String name);
}
