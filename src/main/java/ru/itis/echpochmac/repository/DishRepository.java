package ru.itis.echpochmac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.model.Dish;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findDishesByCafe(Cafe cafe);

    //List<Dish> findByCafeId(long cafe_id);
}
