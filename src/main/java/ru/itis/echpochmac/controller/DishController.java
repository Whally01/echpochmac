package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.exception.AppException;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.model.Dish;
import ru.itis.echpochmac.model.RoleName;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.DishPayLoad;
import ru.itis.echpochmac.service.impl.CafeService;
import ru.itis.echpochmac.service.impl.DishService;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final CafeService cafeService;

    @Autowired
    public DishController(DishService dishService, CafeService cafeService) {
        this.dishService = dishService;
        this.cafeService = cafeService;
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

    @PostMapping("/add")
    public String addDishes(@RequestBody DishPayLoad dishPayLoad) {

        Dish dish = new Dish(dishPayLoad.getName(), dishPayLoad.getImg(), dishPayLoad.getDescription(), dishPayLoad.getPrice());
        Dish result = dishService.save(dish);

        return "redirect:/cafe-menu";
    }

    /*@GetMapping(path = "/dishes")
    @ResponseBody
    public String getDishesByCafe(@RequestParam(name = "cafe_id") String cafeId) {
        Cafe cafe = cafeService.findById(Long.parseLong(cafeId)).
                orElseThrow(() -> new AppException("User Role not set."));

        List<Dish> list = dishService.findDishesByCafe(cafe);
        return list.toString();
    }*/

    /*@GetMapping(URLs.API + URLs.DISHES)
    public ResponseEntity<?> getDishesByCafeApi(@RequestParam(name = "cafe_id") String cafeId) {

        Cafe cafe = cafeService.findById(Long.parseLong(cafeId)).
                orElseThrow(() -> new AppException("User Role not set."));

        List<Dish> result = dishService.findDishesByCafe(cafe);

        return ResponseEntity.ok(result);
    }*/
}
