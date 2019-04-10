package com.threetopia.menu.service.mapper;

import com.threetopia.menu.service.dto.MealDto;
import com.threetopia.menu.service.dto.OrderAnswerDto;
import com.threetopia.menu.service.dto.OrderRequestDto;
import com.threetopia.menu.service.meal.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Mapper for converting OderRequestDto -> OrderAnswerDto
 * Should be in delivery-calculator module, but because of in-memory problem moved in service module
 */
@Component
public class OrderAnswerMapperImpl implements OrderAnswerMapper {

    private MealService mealService;

    @Autowired
    public OrderAnswerMapperImpl(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public OrderAnswerDto getFromRequest(OrderRequestDto orderRequestDto) {
        Double price = 0.;
        OrderAnswerDto orderAnswerDto = new OrderAnswerDto();
        Map<String, Integer > order = orderRequestDto.getOrder();

        for(String key: order.keySet()) {
            MealDto meal = this.mealService.getByName(key);
            price = price + (meal.getPrice() * order.get(key));
            orderAnswerDto.getMeals().add(meal);
        }

        orderAnswerDto.setPriceWithoutDelivery(price);
        orderAnswerDto.setDeliveryPrice(0.);
        orderAnswerDto.setTotal(0.);

        return orderAnswerDto;
    }
}
