package com.example.apelsinapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private String name;

    @JsonIgnore
    @ManyToOne
    private Category category;

    @Column(length = 20)
    private String description;

    @Column(precision = 6,scale = 2)
    private BigDecimal price;

    @Column(length = 1024)
    private String photo;

}
