package com.threetopia.menu.rest;

import com.threetopia.menu.service.dto.MealDto;
import com.threetopia.menu.service.ingreedient.IngreedientService;
import com.threetopia.menu.service.meal.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * CRUD operations for meal
 */

@Controller
@RequestMapping("/meal")
public class MealController {

    private static final String base = "/meal/";

    @Autowired
    private MealService mealService;

    @Autowired
    private IngreedientService ingreedientService;

    /**
     * Get all meals
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<MealDto>> getAllMeals () {
        return ResponseEntity.ok().body(this.mealService.getAll());
    }

    /**
     * Create new meal
     * @param mealDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<MealDto> createMeal (@RequestBody MealDto mealDto) {

        MealDto dto = this.mealService.save(mealDto);
        return ResponseEntity.created(URI.create(base + dto.getId())).body(dto);
    }

    /**
     * Get meal with given id
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<MealDto> getMeal (@PathVariable("id") Long id) {
        if(this.mealService.getById(id) == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(this.mealService.getById(id));
    }

    /**
     * Update meal with given id
     * NOTE: if you pass empty list as engredients, engredients from meal wont be deleted
     * Empty list of ingredients will just be ignored
     * For deleting all ingredients, you could use this api point to leave just on ingredient
     * and request for removing ingredient to remove last one (Details in ApiDescription.md)
     * @param id
     * @param mealDto
     * @return
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ResponseEntity<MealDto> updateMeal (@PathVariable("id") Long id, @RequestBody MealDto mealDto) {
        if(this.mealService.getById(id) == null)
            return ResponseEntity.notFound().build();

        MealDto dto = this.mealService.update(id,mealDto);

        return ResponseEntity.ok().body(dto);
    }

    /**
     * Deleting meal with id
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<MealDto> deleteMeal (@PathVariable("id") Long id) {
        if(this.mealService.getById(id) == null)
            return ResponseEntity.notFound().build();

        MealDto dto = this.mealService.getById(id);
        this.mealService.delete(id);

        return ResponseEntity.ok().body(dto);
    }

    /**
     * Add ingredient with ingredientId to meal with id
     */
    @RequestMapping(path = "/{id}/ingredient/{ingredientId}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ResponseEntity<MealDto> addIngredientToMeal (@PathVariable("id") Long id, @PathVariable("ingredientId") Long ingredientId) {
        if(this.mealService.getById(id) == null || this.ingreedientService.getById(ingredientId) == null)
            return ResponseEntity.notFound().build();

        MealDto  dto = this.mealService.addIngredientToMeal(ingredientId, id);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * Remove ingredient with ingredientId to meal with id
     */
    @RequestMapping(path = "/{id}/ingredient/{ingredientId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<MealDto> deleteIngredientFromMeal (@PathVariable("id") Long id, @PathVariable("ingredientId") Long ingredientId) {

        if(this.mealService.getById(id) == null || this.ingreedientService.getById(ingredientId) == null)
            return ResponseEntity.notFound().build();

        MealDto  dto = this.mealService.removeIngredientFromMeal(ingredientId,id);
        return ResponseEntity.ok().body(dto);
    }

}
