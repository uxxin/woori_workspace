package dev.rest.service;

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
}
