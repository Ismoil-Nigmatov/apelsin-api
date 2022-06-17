package com.example.apelsinapi.service;

import com.example.apelsinapi.dto.ApiResponse;
import com.example.apelsinapi.entity.Customer;
import com.example.apelsinapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public ApiResponse saveCustomer(Customer customer) {
        Customer save = customerRepository.save(customer);

        return ApiResponse.builder().data(save).message("SAVED!").success(true).build();
    }

    public ApiResponse getAll() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()){
            return ApiResponse.builder().message("NO CUSTOMERS YET").success(true).build();
        }
        return ApiResponse.builder().data(customerList).message("THERE").success(true).build();
    }

    public ApiResponse getById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            return ApiResponse.builder().data(customer).message("THERE!").success(true).build();
        }
        return ApiResponse.builder().success(false).message("NOT FOUND").build();
    }

    public ApiResponse deleteById(Integer id) {
        customerRepository.deleteById(id);

        return ApiResponse.builder().success(true).message("DELETED").build();
    }

    public ApiResponse updateById(Integer id,Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            Customer customer1 = optionalCustomer.get();
            customer1.setName(customer.getName());
            customer1.setCountry(customer.getCountry());
            customer1.setAddress(customer.getAddress());
            customer1.setPhone(customer.getPhone());

            Customer save = customerRepository.save(customer1);

            return ApiResponse.builder().data(save).message("UPDATED").success(true).build();
        }
        return ApiResponse.builder().success(false).message("ERROR").build();
    }
}
