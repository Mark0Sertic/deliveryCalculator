package com.threetopia.menu.rest;

import com.threetopia.menu.service.dto.OrderAnswerDto;
import com.threetopia.menu.service.dto.OrderRequestDto;
import com.threetopia.menu.service.mapper.OrderAnswerMapper;
import com.threetopia.menu.service.meal.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Controller for resolving order request
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    private MealService mealService;

    /**
     * Configuration of ip address of delivery-fee-calculator servis
     * Default port of mentioned server is 8090
     */
    @Value("${calculator.server.ip}")
    private String url;

    private OrderAnswerMapper orderAnswerMapper;

    @Autowired
    public OrderController(MealService mealService, OrderAnswerMapper orderAnswerMapper ) {
        this.mealService = mealService;
        this.orderAnswerMapper = orderAnswerMapper;
    }

    /**
     * Main method in controller
     * Method POST
     * RequestBody -> OrderRequestDto
     * From request body resolve details of order (in-memory database problem)
     * All details send to calculator servis
     * @param orderRequestDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<OrderAnswerDto> createOrder (@RequestBody OrderRequestDto orderRequestDto) {
        if(orderRequestDto.getOrder().size() == 0) return ResponseEntity.badRequest().body(null);

        if(isRequestValid(orderRequestDto)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            OrderAnswerDto orderAnswerDto = this.orderAnswerMapper.getFromRequest(orderRequestDto);
            HttpEntity<OrderAnswerDto> request = new HttpEntity<OrderAnswerDto>(orderAnswerDto, headers);
            ResponseEntity<OrderAnswerDto> response = restTemplate.postForEntity(url, request, OrderAnswerDto.class);
            return response;
        } else {
            return ResponseEntity.badRequest().body(null);
        }


    }

    /**
     * Check if all meals exist before asking for details about them
     * Maybe not the smartest move, in large orders database will be asked multiple times
     * about meal details, first to check if they exist and if they exist then again to get details
     * ONE SOLUTION: if they exist put them in list, later use that list instead of asking database
     * @param orderRequestDto
     * @return
     */
    private boolean isRequestValid(OrderRequestDto orderRequestDto) {
        boolean exist = true;
        for(String key : orderRequestDto.getOrder().keySet()) {
            if(this.mealService.getByName(key) == null) {
                exist = false;
                break;
            }
        }

        return  exist;
    }

}
