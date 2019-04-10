package com.threetopia.menu.service.meal.impl;

import com.threetopia.menu.entity.Ingredient;
import com.threetopia.menu.entity.Meal;
import com.threetopia.menu.repo.IngredientRepository;
import com.threetopia.menu.repo.MealRepository;
import com.threetopia.menu.service.dto.MealDto;
import com.threetopia.menu.service.mapper.MealMapper;
import com.threetopia.menu.service.meal.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for handling database access for meal objects
 * NOTE: Should implement interface for later on possible multiple implementations
 */

@Service
@Transactional
public class MealServiceImpl implements MealService {
    
    private MealMapper mealMapper;
    private MealRepository mealRepository;
    private IngredientRepository ingredientRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, MealMapper mealMapper, IngredientRepository ingredientRepository) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public MealDto save(MealDto mealDto) {
        Meal entity = this.mealMapper.getEntiyFromDto(mealDto);
        Meal meal = this.mealRepository.save(entity);
        return this.mealMapper.getDto(meal);
    }

    @Override
    public MealDto update(Long id, MealDto mealDto) {
        Meal entity = this.mealRepository.findById(id).get();
        this.mealMapper.updateEntity(entity, mealDto);
        Meal meal = this.mealRepository.save(entity);
        return this.mealMapper.getDto(meal);
    }

    @Override
    public void delete(Long id) {
        this.mealRepository.deleteById(id);
    }

    @Override
    public List<MealDto> getAll() {
        List<MealDto> dtos = new ArrayList<>();

        this.mealRepository.findAll().forEach(in -> {
            dtos.add(this.mealMapper.getDto(in));
        });

        return dtos;
    }

    @Override
    public MealDto getByName(String name) {
        return this.mealMapper.getDto(this.mealRepository.findByName(name));

    }

    @Override
    public MealDto getById(Long id) {
        if(this.mealRepository.findById(id).isPresent()) {
            return this.mealMapper.getDto(this.mealRepository.findById(id).get());
        } else {
            return null;
        }
    }

    @Override
    public MealDto addIngredientToMeal(Long ingredientId, Long mealId) {
        Meal meal = this.mealRepository.findById(mealId).get();
        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).get();

        meal.addIngredient(ingredient);
        this.mealRepository.save(meal);

        return this.mealMapper.getDto(meal);
    }

    @Override
    public MealDto removeIngredientFromMeal(Long ingredientId, Long mealId) {
        Meal meal = this.mealRepository.findById(mealId).get();
        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).get();

        meal.removeIngredient(ingredient);
        this.mealRepository.save(meal);

        return this.mealMapper.getDto(meal);
    }
}
