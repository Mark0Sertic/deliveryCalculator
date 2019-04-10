package com.threetopia.menu.deliverycalculator.service;

import com.threetopia.menu.service.dto.OrderAnswerDto;
import com.threetopia.menu.service.dto.OrderRequestDto;

public interface DeliveryFeeCalculator {
    OrderAnswerDto calculateFee(OrderAnswerDto orderAnswerDto);
}
