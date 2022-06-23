package com.example.apelsinapi.controller;

import com.example.apelsinapi.dto.ApiResponse;
import com.example.apelsinapi.dto.PaymentDTO;
import com.example.apelsinapi.entity.Payment;
import com.example.apelsinapi.service.PaymentService;
import org.springframework.aop.framework.AopInfrastructureBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ApiResponse addPayment(@RequestBody PaymentDTO paymentDTO){
     return paymentService.add(paymentDTO);
    }

    @GetMapping
    public ApiResponse getPayments(){
       return paymentService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deletePAyment(@PathVariable Integer id){
        return paymentService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ApiResponse editPayment(@PathVariable Integer id,@RequestBody PaymentDTO paymentDTO){
        return paymentService.update(id,paymentDTO);
    }

    @GetMapping("{id}")
    public ApiResponse getPayment(@PathVariable Integer id){
        return paymentService.getById(id);
    }
}
