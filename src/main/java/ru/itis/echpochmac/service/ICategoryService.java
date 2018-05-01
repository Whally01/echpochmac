package ru.itis.echpochmac.service;

import ru.itis.echpochmac.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Optional<Category> findCategoryByName(String categoryName);
    Category save(Category category);
}
