package com.threetopia.menu.deliverycalculator.deliverycalculator;

import com.threetopia.menu.deliverycalculator.service.impl.CalculateFixFee;
import com.threetopia.menu.deliverycalculator.service.impl.CalculatePercentageFee;
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
@ActiveProfiles("percentage")
public class CalculatePercentageFeeTest {
    @Autowired
    private CalculatePercentageFee calculatePercentageFee;

    @Test
    public void calculateFeeTest() {
        OrderAnswerDto orderAnswerDto = new OrderAnswerDto();
        orderAnswerDto.setPriceWithoutDelivery(2.0);
        this.calculatePercentageFee.calculateFee(orderAnswerDto);

        assertTrue(orderAnswerDto.getTotal() ==
                (orderAnswerDto.getDeliveryPrice() + orderAnswerDto.getPriceWithoutDelivery()));
    }
}
