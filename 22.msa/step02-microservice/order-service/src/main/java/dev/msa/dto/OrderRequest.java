package dev.msa.dto;

public record OrderRequest(
        String itemName,
        Integer quantity,
        Double price
) {}
