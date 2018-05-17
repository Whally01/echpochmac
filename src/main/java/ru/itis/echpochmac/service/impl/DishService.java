package ru.itis.echpochmac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.model.Dish;
import ru.itis.echpochmac.repository.DishRepository;
import ru.itis.echpochmac.service.IDishService;

import java.util.List;
import java.util.Optional;

@Service
public class DishService implements IDishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Optional<Dish> findById(String dishId) {
        return dishRepository.findById(Long.parseLong(dishId));
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public List<Dish> findDishesByCafe(Cafe cafe) {
        return dishRepository.findDishesByCafe(cafe);
    }

    @Override
    public void removeDishById(String dishId) {
        dishRepository.removeDishById(Long.parseLong(dishId));
    }


}
