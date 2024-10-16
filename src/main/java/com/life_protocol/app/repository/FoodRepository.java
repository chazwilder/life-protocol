
package com.life_protocol.app.repository;

import com.life_protocol.app.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {
    List<Food> findByNameContainingIgnoreCase(String name);
    List<Food> findByCategory(String category);
    List<Food> findByBrand(String brand);
}