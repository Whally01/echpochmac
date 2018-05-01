package ru.itis.echpochmac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    @GetMapping("/couriers")
    public String couriers() {
        return "couriers";
    }

    @GetMapping("/clients")
    public String clients() {
        return "clients";
    }
}
