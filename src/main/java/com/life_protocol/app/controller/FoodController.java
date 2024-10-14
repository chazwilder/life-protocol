package com.life_protocol.app.controller;

import com.life_protocol.app.model.Food;
import com.life_protocol.app.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@Tag(name = "Food Management", description = "Operations pertaining to food items in the Life Protocol application")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    @Operation(summary = "Create a new food item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Food item successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Food> createFood(@Parameter(description = "Food object to be created", required = true) @RequestBody Food food) {
        Food createdFood = foodService.createFood(food);
        return new ResponseEntity<>(createdFood, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a food item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved food item"),
            @ApiResponse(responseCode = "404", description = "Food item not found")
    })
    public ResponseEntity<Food> getFoodById(@Parameter(description = "ID of the food item to be retrieved", required = true) @PathVariable String id) {
        return foodService.getFoodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all food items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of food items")
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = foodService.getAllFoods();
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/search")
    @Operation(summary = "Search food items by name")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of food items")
    public ResponseEntity<List<Food>> searchFoodsByName(@Parameter(description = "Name to search for", required = true) @RequestParam String name) {
        List<Food> foods = foodService.searchFoodsByName(name);
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get food items by category")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of food items")
    public ResponseEntity<List<Food>> getFoodsByCategory(@Parameter(description = "Category of food items to retrieve", required = true) @PathVariable String category) {
        List<Food> foods = foodService.getFoodsByCategory(category);
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/brand/{brand}")
    @Operation(summary = "Get food items by brand")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of food items")
    public ResponseEntity<List<Food>> getFoodsByBrand(@Parameter(description = "Brand of food items to retrieve", required = true) @PathVariable String brand) {
        List<Food> foods = foodService.getFoodsByBrand(brand);
        return ResponseEntity.ok(foods);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a food item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Food item successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Food item not found")
    })
    public ResponseEntity<Food> updateFood(
            @Parameter(description = "ID of the food item to be updated", required = true) @PathVariable String id,
            @Parameter(description = "Updated food object", required = true) @RequestBody Food food) {
        if (!id.equals(food.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Food updatedFood = foodService.updateFood(food);
        return ResponseEntity.ok(updatedFood);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a food item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Food item successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Food item not found")
    })
    public ResponseEntity<Void> deleteFood(@Parameter(description = "ID of the food item to be deleted", required = true) @PathVariable String id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}