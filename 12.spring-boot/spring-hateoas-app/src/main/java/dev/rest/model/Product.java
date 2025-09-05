package dev.rest.model;

import dev.rest.dto.ProductRequest;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "products")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private int price;
    @Setter
    private int stock;
    @Setter
    private String category;

    public Product(String name, String description, int price, int stock, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static Product from(ProductRequest request) {
        return new Product(
                request.name(),
                request.description(),
                request.price(),
                request.stock(),
                request.category()
        );
    }

}