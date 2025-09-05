package dev.rest.repository;


import dev.rest.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// 상품 도메인을 관리하는 repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //쿼리 메서드(카멜 케이스 규칙, Entity에 존재하는 필드명을 조합하면 그에 해당하는 쿼리가 생성됨)
    Page<Product> findByCategory(String category, Pageable pageable);
}
