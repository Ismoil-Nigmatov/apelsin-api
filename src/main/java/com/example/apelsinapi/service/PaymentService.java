package com.example.apelsinapi.service;

import com.example.apelsinapi.dto.ApiResponse;
import com.example.apelsinapi.dto.PaymentDTO;
import com.example.apelsinapi.entity.Invoice;
import com.example.apelsinapi.entity.Payment;
import com.example.apelsinapi.repository.InvoiceRepository;
import com.example.apelsinapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    InvoiceRepository invoiceRepository;
    public ApiResponse add(PaymentDTO paymentDTO) {
        Invoice invoice = invoiceRepository.getById(paymentDTO.getInvoiceId());
        Payment payment=new Payment();
        payment.setAmount(paymentDTO.getAmount());
        payment.setInvoice(invoice);
        Payment save = paymentRepository.save(payment);
        PaymentDTO paymentDTO1 = paymentToDto(save);
        return ApiResponse.builder().success(true).message("ADDED!").data(paymentDTO1).build();
    }

    public PaymentDTO paymentToDto(Payment payment){
        PaymentDTO paymentDTO=new PaymentDTO();
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setInvoiceId(payment.getInvoice().getId());
        return paymentDTO;
    }

    public ApiResponse getAll() {
        List<Payment> all = paymentRepository.findAll();
        List<PaymentDTO>paymentDTOS=new ArrayList<>();
        for (Payment payment : all) {
            PaymentDTO paymentDTO = paymentToDto(payment);
            paymentDTOS.add(paymentDTO);
        }
        return ApiResponse.builder().data(paymentDTOS).message("THERE!").success(true).build();
    }

    public ApiResponse delete(Integer id) {
        paymentRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("DELETED!").build();
    }

    public ApiResponse update(Integer id, PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.getById(id);
        payment.setAmount(paymentDTO.getAmount());
        payment.setInvoice(invoiceRepository.getById(paymentDTO.getInvoiceId()));
        Payment save = paymentRepository.save(payment);

        return ApiResponse.builder().success(true).message("UPDATED!").data(save).build();

    }

    public ApiResponse getById(Integer id) {
        Payment byId = paymentRepository.getById(id);
        PaymentDTO paymentDTO = paymentToDto(byId);
        return ApiResponse.builder().success(true).message("THERE!").data(paymentDTO).build();
    }
}
