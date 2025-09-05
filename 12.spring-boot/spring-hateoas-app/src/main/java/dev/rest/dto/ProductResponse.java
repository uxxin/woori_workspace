package dev.rest.dto;

import dev.rest.model.Product;

// 상품 객체 응답용 DTO 객체
public record ProductResponse(
        Long id,
        String name,
        String description,
        int price,
        int stock,
        String category
) {
    // DB에서 조회된 Product(엔티티)를 ProductResponse로 변환
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory()
        );
    }
}
