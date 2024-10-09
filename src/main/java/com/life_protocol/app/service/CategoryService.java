package com.life_protocol.app.service;

import com.life_protocol.app.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    Optional<Category> getCategoryByName(String name);
    List<Category> getAllCategories();
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}