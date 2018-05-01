package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.model.Order;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.OrderPayLoad;
import ru.itis.echpochmac.repository.OrderRepository;
import ru.itis.echpochmac.service.impl.OrderService;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderPayLoad orderPayLoad){
        Order order = new Order(orderPayLoad.getQuantity(), orderPayLoad.getPriceOrder(),
                orderPayLoad.getComment(), orderPayLoad.getDestinationAddress());
        Order result = orderService.save(order);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/orders/{add}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Order added successfully"));
    }
}
