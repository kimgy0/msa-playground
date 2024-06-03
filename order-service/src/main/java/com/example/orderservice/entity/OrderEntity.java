package com.example.orderservice.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false, length = 120)
    private String productId;
    @Column(name = "qty", nullable = false)
    private Integer qty;
    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;
    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "order_id", nullable = false, unique = true)
    private String orderId;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createdAt;

}
