package dev.msa.dto;

import java.time.LocalDateTime;

public record PaymentResponse(
    Long paymentId,
    Long orderId,
    Double amount,
    String status,
    LocalDateTime paidAt
) {
}
