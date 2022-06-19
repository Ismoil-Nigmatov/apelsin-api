package com.example.apelsinapi.repository;

import com.example.apelsinapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
