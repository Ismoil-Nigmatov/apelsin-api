package com.example.apelsinapi.controller;

import com.example.apelsinapi.dto.ApiResponse;
import com.example.apelsinapi.dto.OrderDTO;
import com.example.apelsinapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ApiResponse saveOrder(@RequestBody OrderDTO orderDTO){
       return orderService.save(orderDTO);
    }

    @DeleteMapping("{id}")
    public ApiResponse deleteOrder(@PathVariable Integer id){
        return orderService.delete(id);
    }
}
