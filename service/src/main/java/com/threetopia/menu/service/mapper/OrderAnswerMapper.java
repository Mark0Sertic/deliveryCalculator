package com.threetopia.menu.service.mapper;

import com.threetopia.menu.service.dto.OrderAnswerDto;
import com.threetopia.menu.service.dto.OrderRequestDto;

public interface OrderAnswerMapper {
    OrderAnswerDto getFromRequest(OrderRequestDto orderRequestDto);
}
