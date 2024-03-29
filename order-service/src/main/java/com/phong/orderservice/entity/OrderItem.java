package com.phong.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String orderNumber;
    private BigDecimal price;
    private int quantity;
}
