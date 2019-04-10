package com.threetopia.menu.rest;

import com.threetopia.menu.service.dto.IngredientDto;
import com.threetopia.menu.service.ingreedient.IngreedientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * CRUD operations on Ingredient object
 * Details in ApiDescription.md
 */
@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private static final String base = "/ingredient/";

    @Autowired
    private IngreedientService ingreedientService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<IngredientDto>> getAllIngredients () {
        return ResponseEntity.ok().body(this.ingreedientService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<IngredientDto> createIngredient (@RequestBody IngredientDto ingredientDto) {
        IngredientDto ingredient = this.ingreedientService.save(ingredientDto);

        return ResponseEntity.created(URI.create(base + ingredient.getId())).body(ingredient);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<IngredientDto> getIngredient (@PathVariable("id") Long id) {
        if(this.ingreedientService.getById(id) == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(this.ingreedientService.getById(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ResponseEntity<IngredientDto> updateIngredient (@PathVariable("id") Long id, @RequestBody IngredientDto ingredientDto) {
        if(this.ingreedientService.getById(id) == null)
            return ResponseEntity.notFound().build();

        IngredientDto ingredient = this.ingreedientService.update(id, ingredientDto);
        return ResponseEntity.ok().body(ingredient);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<IngredientDto> deleteIngredient (@PathVariable("id") Long id) {
        if(this.ingreedientService.getById(id) == null)
            return ResponseEntity.notFound().build();

        IngredientDto ingredient = this.ingreedientService.getById(id);
        this.ingreedientService.delete(id);
        return ResponseEntity.ok().body(ingredient);
    }
}
