package dev.syntax.step03_constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class TapeReader {

    private Tape tape;

    public TapeReader() {
        System.out.println("TapeReader() 호출됨");
    }

    // TapeReader 인스턴스 생성 과정에서 Tape 타입도 초기화 진행
    // @Autowired //AutoWiring을 생성자를 기반으로 주입하도록 지정
    // -> 생성자가 1개만 존재할 경우, 스프링 4.3 이후부터는 직접 명시하지 않아도 동작
    // https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired.html#beans-autowired-annotation-constructor-resolution
    // -> "As of Spring Framework 4.3,"로 검색
    @Autowired // 생성자가 2개 이상일 경우, 의존성 주입이 적용되길 원하는 메서드 레벨에 직접 명시해야함
    public TapeReader(Tape tape) {
        this.tape = tape;
        System.out.println("TapeReader(Tape tape) 기본 생성자 호출됨");
    }

    public void test() {
        if (tape.isWorked()) {
            System.out.println("tape.getName() = " + tape.getName() + " 정상 동작!");
        } else {
            System.out.println("tape.getName() = " + tape.getName() + " 사기 당함");
        }
    }
}
