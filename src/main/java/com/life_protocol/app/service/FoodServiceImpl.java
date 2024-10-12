package com.life_protocol.app.service;

import com.life_protocol.app.model.Food;
import com.life_protocol.app.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food createFood(Food food) {
        food.setCreatedAt(LocalDateTime.now());
        food.setUpdatedAt(LocalDateTime.now());
        return foodRepository.save(food);
    }

    @Override
    public Optional<Food> getFoodById(String id) {
        return foodRepository.findById(id);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public List<Food> searchFoodsByName(String name) {
        return foodRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Food> getFoodsByCategory(String category) {
        return foodRepository.findByCategory(category);
    }

    @Override
    public List<Food> getFoodsByBrand(String brand) {
        return foodRepository.findByBrand(brand);
    }

    @Override
    public Food updateFood(Food food) {
        food.setUpdatedAt(LocalDateTime.now());
        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(String id) {
        foodRepository.deleteById(id);
    }
}