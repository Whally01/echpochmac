package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.echpochmac.model.User;
import ru.itis.echpochmac.service.impl.UserService;

@Controller
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/couriers/{page}")
    public String couriers(@PathVariable String page, Model model) {
        model.addAttribute("couriers", userService.findAll(Integer.valueOf(page)));
        return "couriers";
    }

    @GetMapping("/clients/{page}")
    public String clients(@PathVariable String page, Model model) {
        model.addAttribute("clients", userService.findAll(Integer.valueOf(page)));
        return "clients";
    }
}
