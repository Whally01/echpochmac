package ru.itis.echpochmac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.echpochmac.model.Order;
import ru.itis.echpochmac.repository.OrderRepository;
import ru.itis.echpochmac.service.IOrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOne(String id) {
        return orderRepository.getOne(Long.parseLong(id));
    }

}
