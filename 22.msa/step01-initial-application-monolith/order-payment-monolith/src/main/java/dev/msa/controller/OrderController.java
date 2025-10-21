package dev.msa.controller;

import dev.msa.dto.OrderRequest;
import dev.msa.dto.OrderResponse;
import dev.msa.domain.Order;
import dev.msa.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        Order order = orderService.createOrder(request);

        return new OrderResponse(
                order.getId(),
                order.getItemName(),
                order.getQuantity(),
                order.getPrice(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {

        return orderService.getOrderById(id)
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getItemName(),
                        order.getQuantity(),
                        order.getPrice(),
                        order.getStatus(),
                        order.getCreatedAt()
                ))
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
