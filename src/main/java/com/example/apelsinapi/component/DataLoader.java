package com.example.apelsinapi.component;

import com.example.apelsinapi.entity.Category;
import com.example.apelsinapi.entity.Customer;
import com.example.apelsinapi.repository.CategoryRepository;
import com.example.apelsinapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class DataLoader implements CommandLineRunner {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Value("spring.sql.init.mode")
    String mode;

    @Override
    public void run(String... args) throws Exception {
        if(mode.equals("always")){
            customerRepository.save(new Customer(1,"Abdulloh","UZB","Oqlon","1234567"));
            customerRepository.save(new Customer(2,"Said","UZB","Kokcha","7654321"));
            categoryRepository.save(new Category(1,"PHONE",null));
        }
    }
}
