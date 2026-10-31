package com.embarkx.FirstSpring.dtos;

import com.embarkx.FirstSpring.models.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponce {
    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private LocalDateTime createdAt;
}
