package com.threetopia.menu.repo;

import com.threetopia.menu.entity.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {
    Meal findByName(String name);
}
