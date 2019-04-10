package com.threetopia.menu.service.mapper;

import com.threetopia.menu.entity.Ingredient;
import com.threetopia.menu.entity.Meal;
import com.threetopia.menu.repo.IngredientRepository;
import com.threetopia.menu.service.dto.IngredientDto;
import com.threetopia.menu.service.dto.MealDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MealMapperTest {
    private MealMapper mealMapper;

    @Before
    public void setUp() {
        long id = 1;
        IngredientRepository ingredientRepository = mock(IngredientRepository.class);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName("testName");
        ingredient.setMeals(new HashSet<>());

        when(ingredientRepository.findByName(any())).thenReturn(ingredient);

        this.mealMapper = new MealMapper(ingredientRepository);
    }

    @Test
    public void getDtoTest() {
        Meal meal = new Meal();
        long id = 1;

        meal.setName("testName");
        meal.setPrice(2.0);
        meal.setIngredients(new HashSet<>());
        meal.setId(id);

        MealDto dto = this.mealMapper.getDto(meal);

        assertTrue(dto.getName().equals(meal.getName()));
        assertTrue(dto.getPrice() == meal.getPrice());
    }

    @Test
    public void getEntiyFromDtoTest() {
        MealDto dto = new MealDto();
        long id = 1;

        dto.setName("testName");
        dto.setPrice(2.0);
        dto.setId(id);

        Meal meal = this.mealMapper.getEntiyFromDto(dto);

        assertTrue(dto.getName().equals(meal.getName()));
        assertTrue(dto.getPrice() == meal.getPrice());
    }

    @Test
    public void updateEntityTest() {
        Meal meal = new Meal();
        long id = 1;

        meal.setId(id);
        meal.setName("testName");
        meal.setPrice(2.0);

        MealDto dto = new MealDto();
        dto.setPrice(3.0);
        dto.setName("testName1");
        dto.setIngredients(new ArrayList<>());

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(id);
        ingredientDto.setName("testName2");

        dto.getIngredients().add(ingredientDto);

        this.mealMapper.updateEntity(meal, dto);

        assertTrue(dto.getName().equals(meal.getName()));
        assertTrue(dto.getPrice() == meal.getPrice());

    }
}
