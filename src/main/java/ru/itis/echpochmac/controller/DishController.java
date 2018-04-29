package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.exception.AppException;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.model.Category;
import ru.itis.echpochmac.model.Dish;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.DishPayLoad;
import ru.itis.echpochmac.repository.CafeRepository;
import ru.itis.echpochmac.repository.CategoryRepository;
import ru.itis.echpochmac.repository.DishRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dishes")
public class DishController {
//    @GetMapping
//    public ModelAndView dishes(){
//        ModelAndView modelAndView = new ModelAndView();
//        List list = new ArrayList();
//        modelAndView.addObject("dishes", list);
//        modelAndView.setViewName("dishes");
//        return modelAndView;
//    }

    @Autowired
    DishRepository dishRepository;

    @Autowired
    CafeRepository cafeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/addDishes")
    public ResponseEntity<?> addDish(@RequestBody DishPayLoad dishPayLoad){


        //Create Dish
        Dish dish = new Dish(dishPayLoad.getName(), dishPayLoad.getImg(), dishPayLoad.getDescription(), dishPayLoad.getPrice());

        Dish result = dishRepository.save(dish);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/dishes/{add}")
                .buildAndExpand(result.getName()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Dish added successfully"));
    }
}
