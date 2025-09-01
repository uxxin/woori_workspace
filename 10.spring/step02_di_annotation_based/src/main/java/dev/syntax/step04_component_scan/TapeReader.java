package dev.syntax.step04_component_scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 스프링에게 "TapeReader는 스프링 컨테이너에서 빈 객체로 관리할 컴포넌트"임을 명시
public class TapeReader {

    private Tape tape;

    public TapeReader() {
        System.out.println("TapeReader() 호출됨");
    }

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
