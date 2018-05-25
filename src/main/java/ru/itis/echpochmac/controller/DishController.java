package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.exception.AppException;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.model.Dish;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.DishPayLoad;
import ru.itis.echpochmac.service.impl.CafeService;
import ru.itis.echpochmac.service.impl.DishService;
import ru.itis.echpochmac.util.URLs;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
public class DishController {

    private final DishService dishService;
    private final CafeService cafeService;

    @Autowired
    public DishController(DishService dishService, CafeService cafeService) {
        this.dishService = dishService;
        this.cafeService = cafeService;
    }

//    @DeleteMapping(URLs.DELETE + "/{dishId}")
//    public String deleteDish(@PathVariable String dishId){
//        dishService.deleteDishById(Long.parseLong(dishId));
//        return "redirect:/cafes";
//    }

    /**
     * Добавление блюда
     */
    @PostMapping(URLs.CAFES + URLs.CAFE + "/{cafeId}" + URLs.DISHES + URLs.ADD)
    public String addDish(@ModelAttribute("dishPayLoad") DishPayLoad dishPayLoad, @PathVariable String cafeId) {
        Cafe cafe = cafeService.findById(Long.parseLong(cafeId)).
                orElseThrow(() -> new AppException("Cafe not found!"));
        Dish dish = new Dish(dishPayLoad.getName(), dishPayLoad.getImg(), dishPayLoad.getDescription(), dishPayLoad.getPrice());
        dish.setCafe(cafe);
        Dish result = dishService.save(dish);

        return "redirect:/cafes/cafe/{cafeId}/dishes";
    }


    /**
     * Получаем конкретное блюдо по его id
     *
     * @param id - идентификатор блюда
     */
    @GetMapping(URLs.DISH + "/{id}")
    public String getDish(@PathVariable String id, Model model) {
        Optional<Dish> dish = dishService.findById(id);
        model.addAttribute("dish", dish);
        return "cafe-menu";
    }

    @DeleteMapping(URLs.DISH + "/{id}" + URLs.DELETE)
    public String deleteDish(@PathVariable String id) {
//        Optional<Dish> dish = dishService.findById(id);
//        model.addAttribute("dish", dish);
        dishService.removeDishById(id);
        return "redirect:/cafes";
    }

    /**
     * Получаем список блюд выбранного кафе по его id
     *
     * @param cafeId - идентификатор Кафе
     */
    @GetMapping(URLs.CAFES + URLs.CAFE + "/{cafeId}" + URLs.DISHES)
    public String getDishesByCafe(Model model, @PathVariable(name = "cafeId") String cafeId) {
        Optional<Cafe> cafe = cafeService.findById(Long.parseLong(cafeId));
        model.addAttribute("cafe", cafe);
        model.addAttribute("dishPayLoad", new DishPayLoad());
        return "cafe-menu";
    }

    @GetMapping(path = "/dishes")
    @ResponseBody
    public String getDishesByCafe(@RequestParam(name = "cafe_id") String cafeId) {
        Cafe cafe = cafeService.findById(cafeId).
                orElseThrow(() -> new AppException("User Role not set."));

        List<Dish> list = dishService.findDishesByCafe(cafe);
        return list.toString();
    }

    /**
     * Получаем список блюд выбранного кафе по его id;
     *
     * @param cafeId - идентификатор Кафе;
     * @apiNote REST API for iOS
     */
    @GetMapping(URLs.API + URLs.DISHES)
    public ResponseEntity<?> getDishesByCafeApi(@RequestParam(name = "cafe_id") String cafeId) {

        Cafe cafe = cafeService.findById(Long.parseLong(cafeId)).
                orElseThrow(() -> new AppException("Cafe not found"));

        List<Dish> result = dishService.findDishesByCafe(cafe);

        return ResponseEntity.ok(result);
    }
}
