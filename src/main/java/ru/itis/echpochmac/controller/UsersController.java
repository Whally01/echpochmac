package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.echpochmac.exception.AppException;
import ru.itis.echpochmac.model.Role;
import ru.itis.echpochmac.model.RoleName;
import ru.itis.echpochmac.model.User;
import ru.itis.echpochmac.service.impl.RoleService;
import ru.itis.echpochmac.service.impl.UserService;

import org.springframework.data.domain.Pageable;
import ru.itis.echpochmac.util.URLs;

import java.util.Optional;

@Controller
public class UsersController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(URLs.COURIERS + "/{page}")
    public String getCouriers(@PathVariable String page, Model model) {
        Role userRole = roleService.findByName(RoleName.ROLE_COURIER)
                .orElseThrow(() -> new AppException("User Role not set."));
        model.addAttribute("couriers", userService.findUsersByRoles(userRole,  PageRequest.of(Integer.parseInt(page), 25)));
        return "couriers";
    }

    @GetMapping(URLs.CLIENTS + "/{page}")
    public String getClients(@PathVariable String page, Model model) {
        Role userRole = roleService.findByName(RoleName.ROLE_CLIENT)
                .orElseThrow(() -> new AppException("User Role not set."));
        model.addAttribute("clients", userService.findUsersByRoles(userRole,  PageRequest.of(Integer.parseInt(page), 25)));
        return "clients";
    }

    @GetMapping(URLs.COURIERS + URLs.COURIER + "/{id}")
    public String getCourier(@PathVariable String id, Model model) {
        Optional<User> courier = userService.findById(id);
        model.addAttribute("courier", courier);
        return "courier-profile";
    }

    @GetMapping(URLs.CLIENTS + URLs.CLIENT + "/{id}")
    public String getClient(@PathVariable String id, Model model) {
        Optional<User> client = userService.findById(id);
        model.addAttribute("client", client);
        return "client-profile";
    }
}
