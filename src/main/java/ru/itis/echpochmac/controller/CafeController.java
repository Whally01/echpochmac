package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.CafePayLoad;
import ru.itis.echpochmac.service.impl.CafeService;

import java.net.URI;
import java.util.List;

@Controller
public class CafeController {
    private final CafeService cafeService;

    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

   /* @PostMapping("/addCafes")
    public ResponseEntity<?> addCafe(@RequestBody CafePayLoad cafePayLoad){

        Cafe cafe = new Cafe( cafePayLoad.getName(), cafePayLoad.getDescription(), cafePayLoad.getImg());
        Cafe result = cafeService.save(cafe);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/cafe/{add}")
                .buildAndExpand(result.getName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Cafe added sucessufully"));
    }*/

    @PostMapping("/cafes/addCafes")
    public ResponseEntity<ApiResponse> addCafe(@RequestBody CafePayLoad cafePayLoad) {
        Cafe cafe = new Cafe(cafePayLoad.getName(), cafePayLoad.getDescription(), cafePayLoad.getImg());
        Cafe result = cafeService.save(cafe);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/cafes/{add}")
                .buildAndExpand(result.getName()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Cafe added successfully"));
    }

    @GetMapping("/cafes")
    public String cafes(Model model) {
        model.addAttribute("cafes", cafeService.findAll());
        return "cafe";
    }

    @GetMapping(URLs.API + URLs.CAFES)
    public ResponseEntity<?> cafes() {

        List<Cafe> result = cafeService.findAll();

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/cafes")
                .buildAndExpand(result).toUri();
        return ResponseEntity.ok(result);
    }
}
