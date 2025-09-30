package dev.data.model;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;


}