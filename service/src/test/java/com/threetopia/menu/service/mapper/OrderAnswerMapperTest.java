package com.threetopia.menu.service.mapper;

import com.threetopia.menu.service.dto.MealDto;
import com.threetopia.menu.service.dto.OrderAnswerDto;
import com.threetopia.menu.service.dto.OrderRequestDto;
import com.threetopia.menu.service.meal.MealService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderAnswerMapperTest {

    @Test
    public void getFromRequestTest() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        Map<String, Integer> testOrder = new HashMap<>();
        MealDto mealDto = new MealDto();

        mealDto.setPrice(2.0);
        mealDto.setName("test1");

        testOrder.put("test1", 1);


        orderRequestDto.setOrder(testOrder);

        MealService mealService = mock(MealService.class);
        when(mealService.getByName(any())).thenReturn(mealDto);

        OrderAnswerMapper orderAnswerMapper = new OrderAnswerMapperImpl(mealService);
        OrderAnswerDto dto = orderAnswerMapper.getFromRequest(orderRequestDto);

        assertTrue(dto.getPriceWithoutDelivery() == 2.0);
    }
}
