package dev.syntax.step07_java_based_with_scan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component // 다시 컴포넌트 스캔 대상에 포함되도록 작성
public class Tape {

    private String name;

    private boolean isWorked;

    public Tape(@Value("아일랜드") String name, @Value("true") boolean isWorked) {
        this.name = name;
        this.isWorked = isWorked;
        System.out.println("Tape (name, isWorked) 호출됨");

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
