package ru.itis.echpochmac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.echpochmac.model.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
