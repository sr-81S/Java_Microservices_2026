package com.embarkx.FirstSpring.services;

import com.embarkx.FirstSpring.dtos.OrderItemDto;
import com.embarkx.FirstSpring.dtos.OrderResponce;
import com.embarkx.FirstSpring.models.CartItem;
import com.embarkx.FirstSpring.models.Order;
import com.embarkx.FirstSpring.models.OrderItem;
import com.embarkx.FirstSpring.models.OrderStatus;
import com.embarkx.FirstSpring.repository.OrderRepositries;
import com.embarkx.FirstSpring.repository.UserRepositries;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepositries orderRepositries;
    private final CartService cartService;
    private final UserRepositries userRepositries;

    @Transactional
    public OrderResponce createOrder(String userId) {
        //validate for cart items
        List<CartItem> cartItems = cartService.getCartForUser(userId);
        if (cartItems == null || cartItems.isEmpty()) {
            return null;
        }
        //validate user

        var user =  userRepositries.findById(Long.valueOf(userId)).orElse(null);
        if (user == null) {
            return null;
        }

        //calculate total price
        var totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        //create order
        var order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                )).toList();
        order.setItems(orderItems);
        //save order
        var savedOrder = orderRepositries.save(order);

        //clear the cart
        cartService.clearCartForUser(userId);

        return mapToOrderResponce(savedOrder);
    }

    private OrderResponce mapToOrderResponce(Order savedOrder) {
        // Implementation for mapping Order to OrderResponce
        OrderResponce orderResponce = new OrderResponce();
        orderResponce.setId(savedOrder.getId());
        orderResponce.setCreatedAt(savedOrder.getCreatedAt());
        orderResponce.setTotalAmount(savedOrder.getTotalAmount());
        orderResponce.setStatus(savedOrder.getStatus());
        orderResponce.setItems(
                savedOrder.getItems().stream()
                        .map(item -> {
                            var orderItemDto = new OrderItemDto();
                            orderItemDto.setId(item.getId());
                            orderItemDto.setProductId(item.getProduct().getId());
                            orderItemDto.setQuantity(item.getQuantity());
                            orderItemDto.setPrice(item.getPrice());
                            return orderItemDto;
                        })
                        .collect(Collectors.toList())
        );

        return orderResponce;
    }
}
