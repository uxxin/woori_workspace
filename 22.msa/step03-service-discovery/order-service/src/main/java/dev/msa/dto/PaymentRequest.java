package dev.msa.dto;

public record PaymentRequest(
    Long orderId,
    Double amount
) {
}
