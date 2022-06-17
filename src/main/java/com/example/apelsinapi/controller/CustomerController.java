package com.example.apelsinapi.controller;

import com.example.apelsinapi.dto.ApiResponse;
import com.example.apelsinapi.entity.Customer;
import com.example.apelsinapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ApiResponse saveCustomer(@RequestBody Customer customer){
       return customerService.saveCustomer(customer);
    }

    @GetMapping("/all")
    public ApiResponse allCustomer(){
         return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Integer id){
        return customerService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteById(@PathVariable Integer id){
        return customerService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateById(@PathVariable Integer id,@RequestBody Customer customer){
        return customerService.updateById(id,customer);
    }
}
