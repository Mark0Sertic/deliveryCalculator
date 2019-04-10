package com.threetopia.menu.deliverycalculator.rest;

import com.threetopia.menu.deliverycalculator.service.DeliveryFeeCalculator;
import com.threetopia.menu.service.dto.OrderAnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Rest point exposed to WebApplication servis for calculating delivery price
 */
@Controller
@RequestMapping("/delivery-calculator")
public class DeliveryFeeController {

    private DeliveryFeeCalculator deliveryFeeCalculator;

    @Autowired
    public DeliveryFeeController(DeliveryFeeCalculator deliveryFeeCalculator) {
        this.deliveryFeeCalculator = deliveryFeeCalculator;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<OrderAnswerDto> createMeal (@RequestBody OrderAnswerDto orderAnswerDto) {
        OrderAnswerDto answer = this.deliveryFeeCalculator.calculateFee(orderAnswerDto);
        return ResponseEntity.ok().body(answer);
    }
}
