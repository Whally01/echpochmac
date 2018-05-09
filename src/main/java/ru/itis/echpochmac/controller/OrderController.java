package ru.itis.echpochmac.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.exception.AppException;
import ru.itis.echpochmac.model.Order;
import ru.itis.echpochmac.model.User;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.OrderPayLoad;
import ru.itis.echpochmac.service.impl.OrderService;
import ru.itis.echpochmac.service.impl.UserService;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/addOrder")
    @ApiOperation(value = "Создание заказа")
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderPayLoad orderPayLoad){
        User user = userService.findById(orderPayLoad.getUserId())
                .orElseThrow(() -> new AppException("User not found"));

        Order order = new Order(Long.parseLong(orderPayLoad.getQuantity()), Double.parseDouble(orderPayLoad.getPriceOrder()),
                orderPayLoad.getComment(), orderPayLoad.getDestinationAddress(), user, orderPayLoad.getDishSet());
        Order result = orderService.save(order);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/orders/{add}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Order added successfully"));
    }

    @GetMapping("/new")
    public String getNewOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders-new";
    }

    @GetMapping("/processing")
    public String getOrdersInProcessing(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders-processing";
    }

    @GetMapping("/order/{id}")
    public String order(@PathVariable("id") String id, Model model){
        Order order = orderService.findOne(id);
        model.addAttribute("order", order);
        return "modal/order :: modalContennts";
    }
}
