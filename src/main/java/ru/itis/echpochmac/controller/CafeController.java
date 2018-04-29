package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.model.Cafe;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.CafePayLoad;
import ru.itis.echpochmac.repository.CafeRepository;

import java.net.URI;

@Controller
@RequestMapping("/cafes")
public class CafeController {
    @Autowired
    CafeRepository cafeRepository;

    @PostMapping("/addCafes")
    public ResponseEntity<?> addCafe(@RequestBody CafePayLoad cafePayLoad){

        Cafe cafe = new Cafe( cafePayLoad.getName(), cafePayLoad.getDescription(), cafePayLoad.getImg());
        Cafe result = cafeRepository.save(cafe);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/cafe/{add}")
                .buildAndExpand(result.getName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Cafe added sucessufully"));
    }
}
