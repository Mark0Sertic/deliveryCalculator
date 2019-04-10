package com.threetopia.menu.service.mapper;

import com.threetopia.menu.entity.Ingredient;
import com.threetopia.menu.service.dto.IngredientDto;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting Ingredient -> IngredientDto, and back
 * NOTE: Should implement interface for later on possible multiple implementations
 */
@Component
public class IngredientMapper {

    public IngredientMapper() {}

    public IngredientDto getDto(Ingredient ingredient) {
        IngredientDto dto = new IngredientDto();
        dto.setId(ingredient.getId());
        dto.setName(ingredient.getName());

        return dto;
    }

    public Ingredient getEntiyFromDto(IngredientDto dto) {
        Ingredient entity = new Ingredient();
        entity.setName(dto.getName());

        return entity;
    }

    public void updateEntity(Ingredient ingredient, IngredientDto dto) {
        ingredient.setName(dto.getName());
    }
}
