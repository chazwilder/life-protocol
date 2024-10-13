package com.life_protocol.app.controller;

import com.life_protocol.app.model.Category;
import com.life_protocol.app.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Api(tags = "Category Management", description = "Operations pertaining to categories in the Life Protocol application")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new category", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Category successfully created"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Category> createCategory(@ApiParam(value = "Category object to be created", required = true) @RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a category by ID", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved category"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<Category> getCategoryById(@ApiParam(value = "ID of the category to be retrieved", required = true) @PathVariable String id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @ApiOperation(value = "Get all categories", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a category", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<Category> updateCategory(
            @ApiParam(value = "ID of the category to be updated", required = true) @PathVariable String id,
            @ApiParam(value = "Updated category object", required = true) @RequestBody Category category) {
        if (!id.equals(category.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Category updatedCategory = categoryService.updateCategory(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a category")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Category successfully deleted"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<Void> deleteCategory(@ApiParam(value = "ID of the category to be deleted", required = true) @PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Get a category by name", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved category"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<Category> getCategoryByName(@ApiParam(value = "Name of the category to be retrieved", required = true) @PathVariable String name) {
        return categoryService.getCategoryByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search categories by name pattern", response = List.class)
    @ApiResponse(code = 200, message = "Successfully retrieved list of categories")
    public ResponseEntity<List<Category>> searchCategories(@ApiParam(value = "Name pattern to search for", required = true) @RequestParam String namePattern) {
        List<Category> categories = categoryService.searchCategories(namePattern);
        return ResponseEntity.ok(categories);
    }
}