package com.threetopia.menu.service.mapper;

import com.threetopia.menu.entity.Ingredient;
import com.threetopia.menu.service.dto.IngredientDto;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class IngredientMapperTest {

    @Test
    public void getDtoTest() {
        long id = 1;
       IngredientMapper ingredientMapper = new IngredientMapper();
       Ingredient ingredient = new Ingredient();

       ingredient.setId(id);
       ingredient.setName("testName");
       ingredient.setMeals(new HashSet<>());

       IngredientDto dto = ingredientMapper.getDto(ingredient);

       assertTrue(dto.getId() == ingredient.getId());
       assertTrue(dto.getName().equals(ingredient.getName()));

    }

    @Test
    public void getEntiyFromDtoTest() {
        IngredientMapper ingredientMapper = new IngredientMapper();
        IngredientDto dto = new IngredientDto();
        long id = 1;

        dto.setName("testName");
        dto.setId(id);

        Ingredient ingredient = ingredientMapper.getEntiyFromDto(dto);

        assertTrue(dto.getName().equals(ingredient.getName()));
    }

    @Test
    public void updateEntityTest() {
        IngredientMapper ingredientMapper = new IngredientMapper();

        long id = 1;
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName("testName");
        ingredient.setMeals(new HashSet<>());

        IngredientDto dto = new IngredientDto();
        long id1 = 1;
        dto.setName("testName1");
        dto.setId(id1);

        ingredientMapper.updateEntity(ingredient,dto);

        assertTrue(dto.getName().equals(ingredient.getName()));
    }
}
