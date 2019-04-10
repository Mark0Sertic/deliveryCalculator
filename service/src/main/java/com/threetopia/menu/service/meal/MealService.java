package com.threetopia.menu.service.meal;

import com.threetopia.menu.service.dto.MealDto;

import java.util.List;

public interface MealService {
    MealDto save(MealDto ingredientDto);
    MealDto update(Long id, MealDto mealDto);
    void delete(Long id);
    List<MealDto> getAll();
    MealDto getByName(String name);
    MealDto getById(Long id);
    MealDto addIngredientToMeal(Long ingredientId, Long mealId);
    MealDto removeIngredientFromMeal(Long ingredientId, Long mealId);
}
