package com.threetopia.menu.service.ingreedient;

import com.threetopia.menu.service.dto.IngredientDto;

import java.util.List;


public interface IngreedientService {
    IngredientDto save(IngredientDto ingredientDto);
    IngredientDto update(Long id, IngredientDto ingredientDto);
    void delete(Long id);
    List<IngredientDto> getAll();
    IngredientDto getByName(String name);
    IngredientDto getById(Long id);
}
