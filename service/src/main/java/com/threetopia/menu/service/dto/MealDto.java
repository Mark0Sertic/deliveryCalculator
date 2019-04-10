package com.threetopia.menu.service.dto;





import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MealDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Double price;
    private List<IngredientDto> ingredients;

    public MealDto() {
        this.ingredients = new ArrayList<>();
    }

    public MealDto(Long id, String name, Double price, List<IngredientDto> ingredients) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
