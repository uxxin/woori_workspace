package dev.syntax.step01_field;

import org.springframework.beans.factory.annotation.Value;

// 비디오 테이프 클래스
public class Tape {
    // @Value를 통해 필드에 값을 직접 주입
    @Value("아일랜드")
    private String name;

    @Value("true")
    private boolean isWorked;

    public Tape() {
        System.out.println("Tape() 호출됨");
    }

    public String getName() {
        return name;
    }

    public boolean isWorked() {
        return isWorked;
    }

    @Override
    public String toString() {
        return "Tape{" +
                "name='" + name + '\'' +
                ", isWorked=" + isWorked +
                '}';
    }
}
