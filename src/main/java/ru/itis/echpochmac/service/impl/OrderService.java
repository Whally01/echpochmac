package ru.itis.echpochmac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.echpochmac.model.Order;
import ru.itis.echpochmac.model.OrderStatus;
import ru.itis.echpochmac.repository.OrderRepository;
import ru.itis.echpochmac.service.IOrderService;

import java.util.List;

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

    @Override
    public Page<Order> findAllByStatus(OrderStatus status, Pageable pageable) {
        return orderRepository.findAllByStatus(status, pageable);
    }

    @Override
    public Page<Order> findAllByStatusOrStatus(OrderStatus delivered, OrderStatus canceled, Pageable pageable) {
        return orderRepository.findAllByStatusOrStatus(delivered, canceled, pageable);
    }

}
