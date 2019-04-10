package com.threetopia.menu.service.dto;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class OrderRequestDto {

    @NotNull
    private Map<String, Integer> order;

    public OrderRequestDto() {
        this.order = new HashMap<>();
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public void setOrder(Map<String, Integer> order) {
        this.order = order;
    }
}
