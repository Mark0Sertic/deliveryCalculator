package com.threetopia.menu.deliverycalculator.service.impl;

import com.threetopia.menu.deliverycalculator.service.DeliveryFeeCalculator;
import com.threetopia.menu.service.dto.OrderAnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Service for percentage price calculation
 */
@Service
@Profile({"percentage", "default"})
public class CalculatePercentageFee implements DeliveryFeeCalculator {

    @Value("${fee.percent}")
    private Double deliveryFee;


    @Autowired
    public CalculatePercentageFee() {

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

        Double deliveryFee = orderAnswerDto.getPriceWithoutDelivery() * (this.deliveryFee / 100);
        Double total = orderAnswerDto.getPriceWithoutDelivery() + deliveryFee;

        orderAnswerDto.setTotal(total);
        orderAnswerDto.setDeliveryPrice(deliveryFee);


        return orderAnswerDto;
    }
}
