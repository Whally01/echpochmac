package ru.itis.echpochmac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.itis.echpochmac.model.Order;
import ru.itis.echpochmac.model.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Order save(Order order);
    List<Order> findAll();
    Order findOne(String id);
    Page<Order> findAllByStatus(OrderStatus status, Pageable pageable);
    Page<Order> findAllByStatusOrStatus(OrderStatus delivered, OrderStatus canceled, Pageable pageable);
}
