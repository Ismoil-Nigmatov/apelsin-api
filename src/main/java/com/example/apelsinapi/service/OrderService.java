package com.example.apelsinapi.service;

import com.example.apelsinapi.dto.ApiResponse;
import com.example.apelsinapi.dto.OrderDTO;
import com.example.apelsinapi.dto.ResOrderDTO;
import com.example.apelsinapi.entity.Customer;
import com.example.apelsinapi.entity.Detail;
import com.example.apelsinapi.entity.Invoice;
import com.example.apelsinapi.entity.Order;
import com.example.apelsinapi.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DetailRepository detailRepository;

    @Autowired
    InvoiceRepository invoiceRepository;
    public ApiResponse save(OrderDTO orderDTO) {
        Order order=new Order();
        order.setCustomer(customerRepository.getById(orderDTO.getCustomerId()));
        order.setDate(new Date());
        Order save = orderRepository.save(order);

        Detail detail=new Detail();
        detail.setOrder(save);
        detail.setProduct(productRepository.getById(orderDTO.getProductId()));
        detail.setQuantity(orderDTO.getQuantity());
        detailRepository.save(detail);

        Invoice invoice=new Invoice();
        invoice.setOrder(save);
        BigDecimal price = detail.getProduct().getPrice();
        Short quantity = detail.getQuantity();
        invoice.setAmount(price.multiply(BigDecimal.valueOf(quantity)));
        invoice.setDue(new Date());
        invoiceRepository.save(invoice);
        ResOrderDTO orderDTO1 = orderToDto(orderDTO.getCustomerId(), invoice, detail);
        return ApiResponse.builder().data(orderDTO1).success(true).message("ADDED").build();
    }

    public ResOrderDTO orderToDto(Integer custId,Invoice invoice,Detail detail){
        ResOrderDTO orderDTO=new ResOrderDTO();

        orderDTO.setCustomerName(customerRepository.getById(custId).getName());
        orderDTO.setProductName(detail.getProduct().getName());
        orderDTO.setQuantity(detail.getQuantity());
        orderDTO.setAmount(invoice.getAmount());
        orderDTO.setIssued(invoice.getIssued());
        orderDTO.setDue(invoice.getDue());
        return orderDTO;
    }

    public ApiResponse delete(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found"));
        Invoice invoice = order.getInvoice();
        List<Detail> detailList = order.getDetail();
        Detail detail = detailList.get(0);
        detailRepository.delete(detail);
        invoiceRepository.delete(invoice);
        orderRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("DELETED!").build();
    }
}
