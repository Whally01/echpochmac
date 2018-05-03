package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
import ru.itis.echpochmac.service.impl.CafeService;
import ru.itis.echpochmac.service.impl.DishService;

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

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }


    @PostMapping("/addDishes")
    public ResponseEntity<?> addDish(@RequestBody DishPayLoad dishPayLoad) {
        Dish dish = new Dish(dishPayLoad.getName(), dishPayLoad.getImg(), dishPayLoad.getDescription(), dishPayLoad.getPrice());
        Dish result = dishService.save(dish);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/dishes/{add}")
                .buildAndExpand(result.getName()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Dish added successfully"));
    }

    @GetMapping(path = "/dishes")
    @ResponseBody
    public String getDishesByCafe(@RequestParam(name = "cafe_id") String cafeId) {
        List<Dish> list = dishService.findDishesByCafeName(Long.valueOf(cafeId));
        return list.toString();
    }
}
