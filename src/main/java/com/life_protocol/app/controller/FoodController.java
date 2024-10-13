package com.life_protocol.app.controller;

import com.life_protocol.app.model.Food;
import com.life_protocol.app.service.FoodService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@Api(tags = "Food Management", description = "Operations pertaining to food items in the Life Protocol application")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new food item", response = Food.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Food item successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Food> createFood(@ApiParam(value = "Food object to be created", required = true) @RequestBody Food food) {
        Food createdFood = foodService.createFood(food);
        return new ResponseEntity<>(createdFood, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a food item by ID", response = Food.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved food item"),
            @ApiResponse(code = 404, message = "Food item not found")
    })
    public ResponseEntity<Food> getFoodById(@ApiParam(value = "ID of the food item to be retrieved", required = true) @PathVariable String id) {
        return foodService.getFoodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @ApiOperation(value = "Get all food items", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of food items")
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = foodService.getAllFoods();
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search food items by name", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of food items")
    public ResponseEntity<List<Food>> searchFoodsByName(@ApiParam(value = "Name to search for", required = true) @RequestParam String name) {
        List<Food> foods = foodService.searchFoodsByName(name);
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/category/{category}")
    @ApiOperation(value = "Get food items by category", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of food items")
    public ResponseEntity<List<Food>> getFoodsByCategory(@ApiParam(value = "Category of food items to retrieve", required = true) @PathVariable String category) {
        List<Food> foods = foodService.getFoodsByCategory(category);
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/brand/{brand}")
    @ApiOperation(value = "Get food items by brand", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of food items")
    public ResponseEntity<List<Food>> getFoodsByBrand(@ApiParam(value = "Brand of food items to retrieve", required = true) @PathVariable String brand) {
        List<Food> foods = foodService.getFoodsByBrand(brand);
        return ResponseEntity.ok(foods);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a food item", response = Food.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Food item successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Food item not found")
    })
    public ResponseEntity<Food> updateFood(
            @ApiParam(value = "ID of the food item to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated food object", required = true) @RequestBody Food food) {
        if (!id.equals(food.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Food updatedFood = foodService.updateFood(food);
        return ResponseEntity.ok(updatedFood);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a food item")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Food item successfully deleted"),
            @ApiResponse(code = 404, message = "Food item not found")
    })
    public ResponseEntity<Void> deleteFood(@ApiParam(value = "ID of the food item to be deleted", required = true) @PathVariable String id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}