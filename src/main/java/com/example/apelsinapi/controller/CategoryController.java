package com.example.apelsinapi.controller;

import com.example.apelsinapi.dto.ApiResponse;
import com.example.apelsinapi.entity.Category;
import com.example.apelsinapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ApiResponse saveCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Integer id){
        return categoryService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteById(@PathVariable Integer id){
        return categoryService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateCategory(@PathVariable Integer id,@RequestBody Category category){
    return categoryService.update(id,category);
    }

    @GetMapping("/all")
    public ApiResponse getAllCategory(){
       return categoryService.getAll();
    }
}
