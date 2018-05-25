package ru.itis.echpochmac.service;

import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.model.Dish;

import java.util.List;
import java.util.Optional;

public interface IDishService {
    Dish save(Dish dish);

    Optional<Dish> findById(String dishId);

    List<Dish> findAll();

    List<Dish> findDishesByCafe(Cafe cafe);

    void removeDishById(String dish_id);
}
