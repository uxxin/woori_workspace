package dev.rest.hateoas;

import dev.rest.controller.ProductController;
import dev.rest.dto.ProductResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.LinkBuilderFactory;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// HATEOAS 관련 설정을 도와주는 역할 수행
@Component // 빈으로 등록
public class ProductResponseAssembler implements RepresentationModelAssembler<ProductResponse, EntityModel<ProductResponse>> {

    private final LinkBuilderFactory linkBuilderFactory;

    public ProductResponseAssembler(LinkBuilderFactory linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

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

}
