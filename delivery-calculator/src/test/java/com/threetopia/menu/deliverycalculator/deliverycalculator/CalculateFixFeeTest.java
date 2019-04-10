package com.threetopia.menu.deliverycalculator.deliverycalculator;

import com.threetopia.menu.deliverycalculator.service.impl.CalculateFixFee;
import com.threetopia.menu.service.dto.OrderAnswerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("fix")
public class CalculateFixFeeTest {
    @Autowired
    private CalculateFixFee calculateFixFee;

    @Test
    public void calculateFeeTest() {
        OrderAnswerDto orderAnswerDto = new OrderAnswerDto();
        orderAnswerDto.setPriceWithoutDelivery(2.0);
        this.calculateFixFee.calculateFee(orderAnswerDto);

        assertTrue(orderAnswerDto.getTotal() ==
                (orderAnswerDto.getDeliveryPrice() + orderAnswerDto.getPriceWithoutDelivery()));
    }
}
