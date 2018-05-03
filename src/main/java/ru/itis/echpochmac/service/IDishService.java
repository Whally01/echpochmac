package ru.itis.echpochmac.service;

import ru.itis.echpochmac.model.Dish;

import java.util.List;

public interface IDishService {
    Dish save(Dish dish);

    List<Dish> findDishesByCafeName(Long id);
}
