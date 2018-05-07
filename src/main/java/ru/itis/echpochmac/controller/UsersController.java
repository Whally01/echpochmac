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
import ru.itis.echpochmac.service.impl.RoleService;
import ru.itis.echpochmac.service.impl.UserService;

import org.springframework.data.domain.Pageable;

@Controller
public class UsersController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/couriers/{page}")
    public String couriers(@PathVariable String page, Model model) {
        Role userRole = roleService.findByName(RoleName.ROLE_COURIER)
                .orElseThrow(() -> new AppException("User Role not set."));
        model.addAttribute("couriers", userService.findUsersByRoles(userRole, new PageRequest(Integer.parseInt(page), 50)));
        return "couriers";
    }

    @GetMapping("/clients/{page}")
    public String clients(@PathVariable String page, Model model) {
        Role userRole = roleService.findByName(RoleName.ROLE_ORDERER)
                .orElseThrow(() -> new AppException("User Role not set."));
        model.addAttribute("clients", userService.findUsersByRoles(userRole, new PageRequest(Integer.parseInt(page), 50)));
        return "clients";
    }
}
