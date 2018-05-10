package ru.itis.echpochmac.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.echpochmac.model.Order;
import ru.itis.echpochmac.model.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByStatus(OrderStatus status, Pageable pageable);
    Page<Order> findAllByStatusOrStatus(OrderStatus delivered, OrderStatus canceled, Pageable pageable);

}
