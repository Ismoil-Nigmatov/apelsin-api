package com.example.apelsinapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 14,nullable = false)
    private String name;

    @Column(length = 3, nullable = false)
    private String country;

    @Column(columnDefinition = "text")
    private String address;

    @Column(length = 50)
    private String phone;
}
