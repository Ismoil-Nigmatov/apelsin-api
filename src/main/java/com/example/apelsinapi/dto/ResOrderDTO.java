package com.example.apelsinapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResOrderDTO {
    private String customerName;

    private String productName;

    private short quantity;

    private BigDecimal amount;

    private Date issued;

    private Date due;
}
