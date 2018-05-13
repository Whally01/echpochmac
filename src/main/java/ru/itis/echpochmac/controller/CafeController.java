package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.model.User;
import ru.itis.echpochmac.payload.CafePayLoad;
import ru.itis.echpochmac.payload.DishPayLoad;
import ru.itis.echpochmac.service.impl.CafeService;
import ru.itis.echpochmac.util.URLs;

import javax.naming.Binding;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(URLs.CAFES)
public class CafeController {
    private final CafeService cafeService;

    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @PostMapping("/addCafes")
    public String addCafe(@ModelAttribute("cafePayLoad") CafePayLoad cafePayLoad) {
        Cafe cafe = new Cafe(cafePayLoad.getName(), cafePayLoad.getDescription());
        Cafe result = cafeService.save(cafe);

        return "redirect:/cafes";
    }

    @GetMapping(URLs.CAFE + "/{id}")
    public String getCourier(@PathVariable String id, Model model) {
        Optional<Cafe> cafe = cafeService.findById(id);
        model.addAttribute("cafe", cafe);
        model.addAttribute("dishPayLoad", new DishPayLoad());
        return "cafe-menu";
    }

    @GetMapping
    public String cafes(Model model) {
        model.addAttribute("cafes", cafeService.findAll());
        model.addAttribute("cafePayLoad", new CafePayLoad());
        return "cafe";
    }

    @GetMapping(URLs.API + URLs.CAFES)
    public ResponseEntity<?> cafes() {
        List<Cafe> result = cafeService.findAll();
        return ResponseEntity.ok(result);
    }
}
