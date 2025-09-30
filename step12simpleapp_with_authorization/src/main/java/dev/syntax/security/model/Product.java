package dev.syntax.security.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double price;

}
