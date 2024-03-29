package com.phong.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> listOrderItems;
}
