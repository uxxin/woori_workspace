package dev.rest.controller;

import dev.rest.dto.ProductResponse;
import dev.rest.model.Product;
import dev.rest.repository.ProductRepository;
import dev.rest.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor // 생성자 주입 with Lombok
public class ProductController {

    private final ProductService productService;

    // TODO: 전체 상품 조회
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts( @RequestParam(required = false) String category,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = productService.getProducts(category, pageable);


        List<ProductResponse> productList = productPage.getContent().stream()
                .map(ProductResponse::from)
                .toList();

        return ResponseEntity.ok(productList);
    }

    // TODO: id로 상품 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse response = productService.getProductById(id);

        return ResponseEntity.ok(response);
    }


    // TODO: 상품 등록

    // TODO: 상품 수정

    // TODO: 상품 제거
}