package com.threetopia.menu.service.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAnswerDto {
    private List<MealDto> meals = new ArrayList<>();
    private Double priceWithoutDelivery;
    private Double deliveryPrice;
    private Double total;

    public OrderAnswerDto() {}

    public OrderAnswerDto(List<MealDto> meals, Double priceWithoutDelivery, Double deliveryPrice, Double total) {
        this.meals = meals;
        this.priceWithoutDelivery = priceWithoutDelivery;
        this.deliveryPrice = deliveryPrice;
        this.total = total;
    }

    public List<MealDto> getMeals() {
        return meals;
    }

    public void setMeals(List<MealDto> meals) {
        this.meals = meals;
    }

    public Double getPriceWithoutDelivery() {
        return priceWithoutDelivery;
    }

    public void setPriceWithoutDelivery(Double priceWithoutDelivery) {
        this.priceWithoutDelivery = priceWithoutDelivery;
    }

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
