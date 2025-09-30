package dev.spring.petclinic.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "owners")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Owner extends Person {
    // id, first_name, last_name은 상위 클래스(Person, BaseEntity)에서 상속받음

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }
}
