package dev.rest.hateoas;

import dev.rest.controller.ProductController;
import dev.rest.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// HATEOAS 관련 설정을 도와주는 역할 수행
@Component // 빈으로 등록
public class ProductResponseAssembler implements RepresentationModelAssembler<ProductResponse, EntityModel<ProductResponse>> {

    // 모든 상품 API 응답에 공통으로 포함시킬 링크 설정 메서드
    @Override
    public EntityModel<ProductResponse> toModel(ProductResponse product) {

        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getProductById(product.id())).withSelfRel(), // Self 링크
                Link.of("/swagger-ui/index.html", "profile") // API 문서 링크
        );
    }

    // 상품 등록 API 응답에 추가할 링크 설정 메서드
    public EntityModel<ProductResponse> toModelForCreate(ProductResponse product) {
        // 공통 링크 적용
        EntityModel<ProductResponse> baseModel = toModel(product);

        // 상품 등록 API에만 포함될 링크 적용
        baseModel.add(linkTo(methodOn(ProductController.class).getProducts(null, 0, 10)).withRel("list-products").withType("GET"));

        return baseModel;
    }

    // TODO: 상품 조회, 수정, 제거와 관련된 HATEOAS 링크 추가

    // 상품 목록 조회 시 페이지네이션 링크 적용
    public Map<String, Link> buildPaginationLinks(String category, int page, int size, Page<?> productPage) {
        String basePath = "/api/products";
        String categoryQuery = (category != null && !category.isBlank()) ? "&category=" + category : "";

        Map<String, Link> links = new LinkedHashMap<>();
        links.put("profile", Link.of("/swagger-ui/index.html"));
        links.put("self", Link.of(basePath + "?page=" + page + "&size=" + size + categoryQuery));

        // 다음 페이지 링크 추가
        if (productPage.hasNext()) {
            links.put("next", Link.of(basePath + "?page=" + (page + 1) + "&size=" + size + categoryQuery));
        }

        // 이전 페이지 링크 추가
        if (productPage.hasPrevious()) {
            links.put("prev", Link.of(basePath + "?page=" + (page - 1) + "&size=" + size + categoryQuery));
        }

        return links;
    }

    // 상품 상세 조회 관련 링크
    public EntityModel<ProductResponse> toModelForDetail(ProductResponse product) {
        EntityModel<ProductResponse> baseModel = toModel(product); // self, profile 링크 포함

        baseModel.add(linkTo(methodOn(ProductController.class).updateProduct(product.id(), null))
                .withRel("update-product").withType("PUT"));
        baseModel.add(linkTo(methodOn(ProductController.class).deleteProduct(product.id()))
                .withRel("delete-product").withType("DELETE"));

        return baseModel;
    }

    // 상품 업데이트 관련 링크
    public EntityModel<ProductResponse> toModelForUpdate(ProductResponse product) {
        EntityModel<ProductResponse> baseModel = toModel(product);

        baseModel.add(linkTo(methodOn(ProductController.class).getProductById(product.id()))
                .withRel("self").withType("GET"));
        baseModel.add(linkTo(methodOn(ProductController.class).getProducts(null, 0, 10))
                .withRel("list-products").withType("GET"));

        return baseModel;
    }

    // 상품 제거 관련 링크
    public EntityModel<ProductResponse> toModelForDelete(ProductResponse product) {
        EntityModel<ProductResponse> baseModel = toModel(product);

        baseModel.add(linkTo(methodOn(ProductController.class).getProducts(null, 0, 10))
                .withRel("list-products").withType("GET"));

        return baseModel;
    }
}