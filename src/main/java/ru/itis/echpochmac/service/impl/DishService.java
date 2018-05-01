package ru.itis.echpochmac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.echpochmac.model.Dish;
import ru.itis.echpochmac.repository.DishRepository;
import ru.itis.echpochmac.service.IDishService;

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
}
