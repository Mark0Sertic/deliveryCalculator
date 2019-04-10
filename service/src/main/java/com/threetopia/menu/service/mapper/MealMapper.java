package com.threetopia.menu.service.mapper;

import com.threetopia.menu.entity.Ingredient;
import com.threetopia.menu.entity.Meal;
import com.threetopia.menu.repo.IngredientRepository;
import com.threetopia.menu.service.dto.IngredientDto;
import com.threetopia.menu.service.dto.MealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting Meal -> MealDto, and back
 * NOTE: Should implement interface for later on possible multiple implementations
 */
@Component
public class MealMapper {
    private IngredientMapper ingredientMapper = new IngredientMapper();
    private IngredientRepository ingredientRepository;

    @Autowired
    public MealMapper(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public MealDto getDto(Meal meal) {
        MealDto dto = new MealDto();
        dto.setId(meal.getId());
        dto.setName(meal.getName());
        dto.setPrice(meal.getPrice());

        meal.getIngredients().forEach(ingredient -> {
            dto.getIngredients().add(ingredientMapper.getDto(ingredient));
        });

        return dto;

    }

    public Meal getEntiyFromDto(MealDto dto) {
        Meal entity = new Meal();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        dto.getIngredients().forEach(ingredientDto -> {
           this.merge(entity, ingredientDto);
        });

        return entity;
    }

    private void merge(Meal entity, IngredientDto dto) {
        Ingredient ingredient = this.ingredientRepository.findByName(dto.getName());
        if(ingredient != null) {
            entity.getIngredients().add(ingredient);
        } else {
            entity.getIngredients().add(ingredientMapper.getEntiyFromDto(dto));
        }
        // entity.addIngredient(ingredientMapper.getEntiyFromDto(dto));
    }

    public void updateEntity(Meal meal, MealDto dto) {
        meal.setPrice(dto.getPrice());
        meal.setName(dto.getName());

        if(dto.getIngredients().size() > 0) {
            meal.getIngredients().clear();
            dto.getIngredients().forEach(in -> {
                this.merge(meal, in);

            });
        }

    }
}
