package ru.itis.echpochmac.service;

import ru.itis.echpochmac.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Order save(Order order);
    List<Order> findAll();
    Order findOne(String id);
}
