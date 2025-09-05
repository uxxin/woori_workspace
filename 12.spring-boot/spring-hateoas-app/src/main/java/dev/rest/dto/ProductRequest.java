package dev.rest.dto;

// 상품 등록 시 활용할 요청 객체 DTO, 추후 유효성 코드 추가 예정
public record ProductRequest(
        String name,
        String description,
        int price,
        int stock,
        String category
) {}
