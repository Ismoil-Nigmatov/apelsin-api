package com.example.apelsinapi.dto;

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
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String description;
    private BigDecimal price;
    private String photo;
}
