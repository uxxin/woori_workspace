package dev.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// 상품 등록 시 활용할 요청 객체 DTO
public record ProductRequest(
        @NotBlank(message = "상품 이름은 필수입니다.") // 공백, 빈문자열 허용 x
        String name,

        @NotBlank(message = "상품 설명은 필수입니다.")
        @Size(max = 1000, message = "상품 설명은 1000자 이하로 입력해주세요.") // 설명 문자열 길이 제한
        String description,

        @Min(value = 0, message = "가격은 0원 이상이어야 합니다.") // 음수 방지
        int price,

        @Min(value = 0, message = "재고는 1개 이상이어야 합니다.")
        int stock,

        @NotBlank(message = "카테고리는 필수입니다.")
        String category
) {}
