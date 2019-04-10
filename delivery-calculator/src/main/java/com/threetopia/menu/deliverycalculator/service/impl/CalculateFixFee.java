package com.threetopia.menu.deliverycalculator.service.impl;

import com.threetopia.menu.deliverycalculator.service.DeliveryFeeCalculator;
import com.threetopia.menu.service.dto.OrderAnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Service for fix price calculation
 */
@Service
@Profile("fix")
public class CalculateFixFee implements DeliveryFeeCalculator {

    @Value("${fee.fix}")
    private Double deliveryFee;


    @Autowired
    public CalculateFixFee() {
    }

    /**
     * This method returns same object which is passed to it
     * This isn't mistake, idea was to implement method in this service which takes OrderRequestDto
     * as param, goes to db and gets meals from order, and then calculates and generates OrderAnswerDto
     * But because of in-memory database problem described in README.md enrichment is done in WebApplication
     * service
     * @param orderAnswerDto
     * @return
     */
    @Override
    public OrderAnswerDto calculateFee(OrderAnswerDto orderAnswerDto) {
        orderAnswerDto.setDeliveryPrice(deliveryFee);
        orderAnswerDto.setTotal(orderAnswerDto.getPriceWithoutDelivery() + orderAnswerDto.getDeliveryPrice());

        return orderAnswerDto;
    }
}
