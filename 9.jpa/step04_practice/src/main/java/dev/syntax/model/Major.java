package dev.syntax.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// 학과(전공) 클래스
@Builder
//@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    /**
     * Student(N)에서 외래키를 가지고 있기 때문에 Student.major가 외래키를 가진 쪽
     * JPA는 외래키를 관리할 때 외래키를 가진 쪽에서만 관리를 함(Student쪽)
     * 반대로 외래키를 가진 쪽이 아닌 Major 클래스의 students는 조회를 위한 용도로 사용되는 필드
     */

//    // 하나의 학과(One)에는 여러 명의 학생(Many)이 소속될 수 있음
//    // 기본 값이 LAZY
//    @OneToMany(mappedBy = "major")  // 반대쪽 매핑(Student의 Major 타입 필드)의 필드 이름을 지정
//
//    @Builder.Default // 이걸 기본값으로 하겠다.
//    private List<Student> students = new ArrayList<>(); // 여기서 이미 초기화 해놔서 위에 @ 쓴거

    // Lazy + 초기화 제거
    @OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
    private List<Student> students;

    // NPE 방지를 위한 헬퍼 메서드
    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }


    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", students=" + students +
                '}';
    }
}
