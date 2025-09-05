package dev.rest.controller;

import dev.rest.dto.ProductRequest;
import dev.rest.dto.ProductResponse;
import dev.rest.hateoas.ProductResponseAssembler;
import dev.rest.model.Product;
import dev.rest.repository.ProductRepository;
import dev.rest.service.ProductService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor // 생성자 주입 with Lombok
public class ProductController {

    private final ProductService productService;
    private final ProductResponseAssembler assembler;

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


    @PostMapping
    public ResponseEntity<EntityModel<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.createProduct(request);

        // HATEOAS 설정
        EntityModel<ProductResponse> model = assembler.toModelForCreate(response);

        return ResponseEntity
                .created(linkTo(methodOn(ProductController.class).getProductById(response.id())).toUri()) // Location 응답 헤더에 해당 리소스의 엔드포인트
                .body(model);
    }

    // TODO: 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id, // 수정하고 싶은 상품 id
            @RequestBody ProductRequest request // 수정하고 싶은 데이터(JSON)
    ) {
        ProductResponse response = productService.updateProduct(id, request);

        return ResponseEntity.ok(response);
    }

    // TODO: 상품 제거
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {

        ProductResponse deleted = productService.deleteProduct(id);

        return ResponseEntity.ok(deleted);
    }
}