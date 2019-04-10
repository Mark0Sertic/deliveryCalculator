package com.threetopia.menu.service.ingreedient.impl;

import com.threetopia.menu.entity.Ingredient;
import com.threetopia.menu.repo.IngredientRepository;
import com.threetopia.menu.service.dto.IngredientDto;
import com.threetopia.menu.service.ingreedient.IngreedientService;
import com.threetopia.menu.service.mapper.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for handling database access for ingredient objects
 * NOTE: Should implement interface for later on possible multiple implementations
 */
@Service
@Transactional
public class IngredientServiceImpl implements IngreedientService {
    private IngredientMapper ingredientMapper;
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }


    @Override
    public IngredientDto save(IngredientDto ingredientDto) {
        Ingredient entity = this.ingredientMapper.getEntiyFromDto(ingredientDto);
        Ingredient ingredient = this.ingredientRepository.save(entity);
        return this.ingredientMapper.getDto(ingredient);
    }

    @Override
    public IngredientDto update(Long id, IngredientDto ingredientDto) {
        Ingredient entity = this.ingredientRepository.findById(id).get();
        this.ingredientMapper.updateEntity(entity,ingredientDto);
        Ingredient ingredient = this.ingredientRepository.save(entity);
        return this.ingredientMapper.getDto(ingredient);
    }

    @Override
    public void delete(Long id) {
        this.ingredientRepository.deleteById(id);
    }

    @Override
    public List<IngredientDto> getAll() {
        List<IngredientDto> dtos = new ArrayList<>();

        this.ingredientRepository.findAll().forEach(in -> {
            dtos.add(this.ingredientMapper.getDto(in));
        });

        return dtos;
    }

    @Override
    public IngredientDto getByName(String name) {
        return this.ingredientMapper.getDto(this.ingredientRepository.findByName(name));

    }

    @Override
    public IngredientDto getById(Long id) {
        if(this.ingredientRepository.findById(id).isPresent()) {
            return this.ingredientMapper.getDto(this.ingredientRepository.findById(id).get());
        } else {
            return null;
        }
    }
}
