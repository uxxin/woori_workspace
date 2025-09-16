package dev.data;

import dev.data.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    // CrudRepository에 선언된 메서드들을 상속받아서 활용 가능

    // 쿼리 메서드
    // find..By 패턴 + 전체 조회(All) + City를 기준으로 조회(City)
    // -> 카멜케이스 규칙으로 메서드를 정의
    // Spring Data에서 메서드 패턴이 규칙을 준수하였는지 확인 후,
    // 준수하였을 경우 그에 맞는 쿼리를 생성해줌
    List<Owner> findAllByCity(String city);

    List<Owner> findAllByCityOrderByLastName(String city);

    boolean existsByFirstName(String firstName);

    long countByCity(String city);

    boolean existsByLastNameAndCity(String lastname,String City);

    List<Owner> findByFirstNameStartingWith(String firstName);
}