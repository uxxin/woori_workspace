package dev.syntax;

import dev.syntax.model.Major;
import dev.syntax.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class AppTest {
    EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("step04");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();


    @Test
    @DisplayName("N+1 문제가 발생한 경우")
    void nPlusOneProblemTest() {
        transaction.begin();

        // 학과 생성
        Major major1 = Major.builder().name("컴퓨터공학").build();
        Major major2 = Major.builder().name("전자공학").build();
        manager.persist(major1);
        manager.persist(major2);

        // 학생 생성
        for (int i = 0; i < 5; i++) {
            Student s = Student.builder().name("학생" + i).build();
            s.setMajor(major1);
            manager.persist(s);
        }

        for (int i = 5; i < 10; i++) {
            Student s = Student.builder().name("학생" + i).build();
            s.setMajor(major2);
            manager.persist(s);
        }

        manager.flush();
        manager.clear(); // 영속성 컨텍스트 초기화 → Lazy Loading 확인용

        System.out.println("=== 조회 시작 (N+1 문제 발생) ===");

        // 모든 학과 조회
        List<Major> majors = manager.createQuery("select m from Major m", Major.class)
                .getResultList();

        // 각 학과의 학생 수 출력 → N+1 쿼리 발생
        for (Major m : majors) {
            System.out.println(m.getName() + " 학생 수: " + m.getStudents().size());
        }

        transaction.commit();
    }

    @Test
    @DisplayName("N+1 문제 해결 테스트")
    void nPlusOneSolvedTest() {

        transaction.begin();

        Major major1 = Major.builder().name("컴퓨터공학").build();
        Major major2 = Major.builder().name("전자공학").build();
        manager.persist(major1);
        manager.persist(major2);

        for (int i = 0; i < 5; i++) {
            Student s = Student.builder().name("학생" + i).build();
            s.setMajor(major1);
            manager.persist(s);
        }

        for (int i = 5; i < 10; i++) {
            Student s = Student.builder().name("학생" + i).build();
            s.setMajor(major2);
            manager.persist(s);
        }

        manager.flush();
        manager.clear(); // Lazy Loading 테스트용

        System.out.println("=== 해결: Fetch Join 사용 ===");

        // Fetch Join으로 학과 + 학생을 한 번에 조회
        List<Major> majors = manager.createQuery(
                        "select distinct m from Major m join fetch m.students", Major.class)
                .getResultList();

        for (Major m : majors) {
            System.out.println(m.getName() + " 학생 수: " + m.getStudents().size());
        }

        transaction.commit();
    }

    @Test
    @DisplayName("LazyInitializationException 발생 테스트")
    void lazyInitializationExceptionOccurs() {
        transaction.begin();

        // 1. Major 생성
        Major major = Major.builder().name("컴퓨터공학").build();
        manager.persist(major);

        // 2. Student 생성
        Student student = Student.builder().name("홍길동").build();
        student.setMajor(major); // 컬렉션 초기화 하지 않음
        manager.persist(student);

        transaction.commit();
        manager.clear(); // 영속성 컨텍스트 초기화

        // 3. Major 조회 (students 컬렉션은 프록시 상태)
        Major foundMajor = manager.find(Major.class, major.getId());

        // 4. 세션 종료
        manager.close();

        // 5. LazyInitializationException 발생 지점
        assertThrows(LazyInitializationException.class, () -> {
            foundMajor.getStudents().size(); // 세션 종료 후 LAZY 접근
        });
    }



    @Test
    @DisplayName("LazyInitializationException 문제 해결")
    void LazyInitializationExceptionSolvedTest() {
        transaction.begin();

        // 학과와 학생 데이터 준비
        Major major = Major.builder().name("전자공학").build();
        manager.persist(major);

        for (int i = 0; i < 3; i++) {
            Student s = Student.builder().name("학생" + i).build();
            s.setMajor(major);
            manager.persist(s);
        }

        transaction.commit();
        manager.clear(); // 영속성 컨텍스트 초기화

        // Fetch Join 사용해서 students 컬렉션을 미리 로딩
        List<Major> majors = manager.createQuery(
                        "select distinct m from Major m join fetch m.students where m.name = :name", Major.class)
                .setParameter("name", "전자공학")
                .getResultList();

        for (Major m : majors) {
            System.out.println(m.getName() + " 학생 수: " + m.getStudents().size());
        }

        manager.close();
    }

}
