package dev.syntax.security.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    private List<Role> roles;
}
