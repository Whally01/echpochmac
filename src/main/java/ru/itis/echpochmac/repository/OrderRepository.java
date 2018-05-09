package ru.itis.echpochmac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.echpochmac.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  //  Page<Order> findAllByStatus(Boolean status, Pageable pageable);

}
