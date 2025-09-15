package dev.data;

import dev.data.model.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test; // JUnit 5 버전
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class JpaPracticeApplicationTests {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("전체 Owner 조회")
    void findAllOwner() {
        // OwnerRepository를 통해 데이터에 접근
        // -> 현재 테스트 클래스에서 OwnerRepository가 의존성으로 필요
        ownerRepository.findAll() // JPA의 메서드 활용
                .forEach(System.out::println); // Iterable 문법
    }

    @Test
    @DisplayName("city 컬럼을 기준으로 해당하는 모든 Owner를 조회")
    void findAllByCity() {
        // CrudRepository, JpaRepository
        // 일반적인 id 기반의 조회는 기본으로 제공하고 있음
        // ex. ownerRepository.findById(1L);

        // TODO: 특정 도메인에 속한 컬럼을 기반으로 조회해야 할 경우?
        List<Owner> owners = ownerRepository.findAllByCity("Madison");
        assertThat(owners).hasSize(4); // "4명일 것이다."
    }

    @Test
    @DisplayName("City에 해당하는 모든 Owner를 조회하되, last_name을 기준으로 오름차순 정렬하여 조회")
    void test2() {
        List<Owner> owners = ownerRepository.findAllByCityOrderByLastName("Madison");

        String firstOwnerName = owners.get(0).getLastName();

        // Madison city에 거주하는 Owner 중에서 오름차순 정렬 시 가장 첫 번째 Owner
        assertThat(firstOwnerName).isEqualTo("Escobito");
    }

    @Test
    @DisplayName("first_name에 해당하는 Owner가 존재하는지 확인")
    void test3() {
        boolean isExists = ownerRepository.existsByFirstName("David");

        assertThat(isExists).isTrue();
    }

    @Test
    @DisplayName("City에 해당하는 Owner의 숫자 조회")
    void test4() {
        String city = "Madison";
        long count = ownerRepository.countByCity(city);

        System.out.println(city + "에 있는 Owner 수: " + count);
        assertThat(count).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("last_name이 ㅇㅇ이고, city가 ㅇㅇ인 Owner 조회(And 조건)")
    void test5() {
        boolean exists = ownerRepository.existsByLastNameAndCity("Davis","Windsor");
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("first_name이 ㅇㅇ으로 시작하는 owner 조회")
    void test6() {
        List<Owner> owners = ownerRepository.findByFirstNameStartingWith("Ge");
        assertThat(owners.size()).isEqualTo(1);
    }
}
