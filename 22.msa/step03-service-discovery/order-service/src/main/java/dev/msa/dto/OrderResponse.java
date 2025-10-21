package dev.msa.dto;

import dev.msa.domain.OrderStatus;
import java.time.LocalDateTime;

public record OrderResponse(
        Long orderId,
        String itemName,
        Integer quantity,
        Double price,
        OrderStatus status,
        LocalDateTime createdAt
) {}
