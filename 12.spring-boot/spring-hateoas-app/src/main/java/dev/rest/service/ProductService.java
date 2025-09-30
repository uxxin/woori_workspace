package dev.rest.service;

import dev.rest.dto.ProductRequest;
import dev.rest.dto.ProductResponse;
import dev.rest.exception.ProductNotFoundException;
import dev.rest.model.Product;
import dev.rest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 빈으로 등록
@RequiredArgsConstructor // 생성자 주입
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getProducts(String category, Pageable pageable) {
        return (category != null && !category.isBlank())
                ? productRepository.findByCategory(category, pageable)
                : productRepository.findAll(pageable);
    }

    public ProductResponse getProductById(Long id) {
        // id로 상품 조회
        // 상품이 존재할 경우 Product 타입의 엔티티를 반환받음(Optional 객체)
        // 존재하지 않을 경우 ProductNotFound 예외 발생
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));

        return ProductResponse.from(product);
    }

    public ProductResponse createProduct(ProductRequest request) {

        // DTO-> Entity 변환(JPA느 Entity로 관리하기 때문에)
        Product newProduct = Product.from(request);

        Product createdProduct = productRepository.save(newProduct);

        // Entity -> DTO 변환(응답 처리용)
        return ProductResponse.from(createdProduct);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));


        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(request.category());

        Product updatedProduct = productRepository.save(product);

        return ProductResponse.from(updatedProduct);
    }

    public ProductResponse deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        // 삭제 전 응답용 DTO로 변환
        ProductResponse deletedProduct = ProductResponse.from(product);

        productRepository.delete(product); // DB에서 삭제

        return deletedProduct;
    }
}
