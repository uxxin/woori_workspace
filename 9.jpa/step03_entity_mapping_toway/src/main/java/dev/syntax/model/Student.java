package dev.syntax.model;

import jakarta.persistence.*;
import lombok.*;

//@Setter
@Getter // lombok 사용
@Builder // 빌더(Builder) 패턴(객체 생성 패턴)
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // 여러 명의 Student(Many)는 하나의 전공(One)에 속함.
    @ManyToOne  // -> 외래키 매핑할 때 사용
    // DB에서는 Student 테이블의 major_id(FK)를 통해 Major 테이블의 특정 레코드를
    @JoinColumn(name = "major_id")
    private  Major major;

    // 학생에게 학과 세팅
    // lombok에 의해 setMajor(Major major){}가 작성됨

    // 연관관계_매핑용_편의_메서드를_활용하여_리팩토링() 시나리오 테스트 검증용 메서드
    public void setMajor(Major major) {
        this.major = major;

        //반대편 연관관계 매핑용 코드를 setMajor() 안쪽에 포함시킴
        major.getStudents().add(this);

        // 여기에 cs.getStudents().add(student);를 쓰면 TEST에서 두번 안써도 됨.
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", major=" + major +
                '}';
    }


}
