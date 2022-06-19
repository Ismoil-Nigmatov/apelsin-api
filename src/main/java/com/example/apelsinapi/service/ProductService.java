package com.example.apelsinapi.service;

import com.example.apelsinapi.dto.ApiResponse;
import com.example.apelsinapi.dto.ProductDTO;
import com.example.apelsinapi.entity.Product;
import com.example.apelsinapi.repository.CategoryRepository;
import com.example.apelsinapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse add(ProductDTO productDTO) {
        Product product=new Product();
        product.setName(productDTO.getName());
        product.setCategory(categoryRepository.getById(productDTO.getCategoryId()));
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        Product save = productRepository.save(product);
        return ApiResponse.builder().success(true).message("ADDED!").data(save).build();
    }

    public ApiResponse getAll() {
        List<Product> all = productRepository.findAll();

        return ApiResponse.builder().data(all).success(true).message("THERE").build();
    }

    public ApiResponse getById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND!"));
        return ApiResponse.builder().data(product).message("THERE").success(true).build();
    }

    public ApiResponse deleteById(Integer id) {
        productRepository.deleteById(id);

        return ApiResponse.builder().success(true).message("DELETED").build();
    }

    public ApiResponse update(Integer id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        product.setName(productDTO.getName());
        product.setCategory(categoryRepository.getById(productDTO.getCategoryId()));
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        Product save = productRepository.save(product);

        return ApiResponse.builder().message("EDITED").success(true).data(save).build();
    }
}
