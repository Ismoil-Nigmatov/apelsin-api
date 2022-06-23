package com.example.apelsinapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Order order;

    @Column(nullable = false,precision = 8,scale = 2)
    private BigDecimal amount;

    @CreatedDate
    @Temporal(value = TemporalType.DATE)
    @Column(nullable = true)
    private Date issued;

    @CreatedDate
    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date due;
}
