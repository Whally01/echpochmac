package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.echpochmac.model.OrderStatus;
import ru.itis.echpochmac.service.impl.OrderService;
import ru.itis.echpochmac.util.URLs;

@Controller
@RequestMapping(URLs.REPORTS)
public class ReportsController {

    private final OrderService orderService;

    @Autowired
    public ReportsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{page}")
    public String getReports(@PathVariable String page, Model model) {
        model.addAttribute("orders", orderService.findAllByStatusOrStatus(OrderStatus.DELIVERED, OrderStatus.CANCELED, PageRequest.of(Integer.parseInt(page), 20)));
        return "reports";
    }
}
