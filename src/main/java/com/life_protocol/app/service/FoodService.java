package com.life_protocol.app.service;

import com.life_protocol.app.model.Food;
import java.util.List;
import java.util.Optional;

public interface FoodService {
    Food createFood(Food food);
    Optional<Food> getFoodById(String id);
    List<Food> getAllFoods();
    List<Food> searchFoodsByName(String name);
    List<Food> getFoodsByCategory(String category);
    List<Food> getFoodsByBrand(String brand);
    Food updateFood(Food food);
    void deleteFood(String id);
}