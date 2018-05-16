package ru.itis.echpochmac.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.exception.AppException;
import ru.itis.echpochmac.model.Order;
import ru.itis.echpochmac.model.OrderStatus;
import ru.itis.echpochmac.model.User;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.OrderPayLoad;
import ru.itis.echpochmac.service.impl.OrderService;
import ru.itis.echpochmac.service.impl.UserService;
import ru.itis.echpochmac.util.URLs;

import javax.validation.Valid;
import java.net.URI;
import java.net.URL;

@Controller
@RequestMapping
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping(URLs.API + URLs.ORDERS + URLs.ADD)
    @ApiOperation(value = "Создание заказа")
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderPayLoad orderPayLoad) {
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

    /**
     * Получение новых {@link OrderStatus#NEW} заказов пострнично
     *
     * @param page - номер страницы
     */
    @GetMapping(URLs.ORDERS + "/new/{page}")
    public String getNewOrders(@PathVariable String page, Model model) {
        model.addAttribute("orders", orderService.findAllByStatus(OrderStatus.NEW, PageRequest.of(Integer.parseInt(page), 20)));
        model.addAttribute("processingCount", orderService.findAllByStatus(OrderStatus.PROCESSING, PageRequest.of(Integer.parseInt(page), 20)).getNumberOfElements());
        return "orders-new";
    }

    /**
     * Получение заказов пострнично, которые в процессе {@link OrderStatus#PROCESSING}
     *
     * @param page - номер страницы
     */
    @GetMapping(URLs.ORDERS + "/processing/{page}")
    public String getOrdersInProcessing(@PathVariable String page, Model model) {
        model.addAttribute("orders", orderService.findAllByStatus(OrderStatus.PROCESSING, PageRequest.of(Integer.parseInt(page), 20)));
        model.addAttribute("newCount", orderService.findAllByStatus(OrderStatus.NEW, PageRequest.of(Integer.parseInt(page), 20)).getNumberOfElements());
        return "orders-processing";
    }

    @GetMapping(URLs.ORDERS + URLs.ARCHIVE + "/{page}")
    public String getArchive(@PathVariable String page, Model model) {
        model.addAttribute("orders", orderService.findAllByStatusOrStatus(OrderStatus.DELIVERED, OrderStatus.CANCELED, PageRequest.of(Integer.parseInt(page), 20)));
        return "archive";
    }

    @GetMapping(URLs.ORDERS + URLs.ORDER + "/{id}")
    public String order(@PathVariable("id") String id, Model model) {
        Order order = orderService.findOne(id);
        model.addAttribute("order", order);
        return "modal/order :: modalContennts";
    }
}
