package ru.itis.echpochmac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {
    @GetMapping
    public ModelAndView dishes(){
        ModelAndView modelAndView = new ModelAndView();
        List list = new ArrayList();
        modelAndView.addObject("dishes", list);
        modelAndView.setViewName("dishes");
        return modelAndView;
    }
}
