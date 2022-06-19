package com.example.apelsinapi.service;

import com.example.apelsinapi.dto.ApiResponse;
import com.example.apelsinapi.entity.Category;
import com.example.apelsinapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.LazyEscapingCharSequence;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse save(Category category) {
        Category save = categoryRepository.save(category);
        return ApiResponse.builder().data(save).success(true).message("SAVED!").build();
    }

    public ApiResponse getById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            return ApiResponse.builder().data(category).message("THERE").success(true).build();
        }
        return ApiResponse.builder().success(false).message("Not Found").build();
    }

    public ApiResponse delete(Integer id) {
        categoryRepository.deleteById(id);
        return ApiResponse.builder().message("DELETED!").success(true).build();
    }

    public ApiResponse update(Integer id, Category category) {
        Category repository = categoryRepository.getById(id);
        repository.setName(category.getName());
        Category save = categoryRepository.save(repository);
        return ApiResponse.builder().data(save).success(true).message("UPDATED").build();
    }

    public ApiResponse getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return ApiResponse.builder().data(categoryList).message("THERE").success(true).build();
    }
}
