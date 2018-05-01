package ru.itis.echpochmac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itis.echpochmac.model.Category;
import ru.itis.echpochmac.payload.ApiResponse;
import ru.itis.echpochmac.payload.CategoryPayLoad;
import ru.itis.echpochmac.repository.CategoryRepository;
import ru.itis.echpochmac.service.impl.CafeService;
import ru.itis.echpochmac.service.impl.CategoryService;

import java.net.URI;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody CategoryPayLoad categoryPayLoad){
        Category category = new Category(categoryPayLoad.getName(), categoryPayLoad.getImg());
        Category result = categoryService.save(category);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/category/{add}")
                .buildAndExpand(result.getName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Category added Successfully"));
    }
}
