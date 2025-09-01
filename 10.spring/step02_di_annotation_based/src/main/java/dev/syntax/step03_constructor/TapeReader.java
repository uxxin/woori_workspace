package dev.syntax.step03_constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class TapeReader {

    private Tape tape;
    
    // TapeReader 인스턴스 생성 과정에서 Tape 타입도 초기화 진행
    @Autowired //AutoWiring을 생성자를 기반으로 주입하도록 지정
    public TapeReader(Tape tape) {
        this.tape = tape;
        System.out.println("TapeReader() 기본 생성자 호출됨");
    }

    public void test() {
        if (tape.isWorked()) {
            System.out.println("tape.getName() = " + tape.getName() + " 정상 동작!");
        } else {
            System.out.println("tape.getName() = " + tape.getName() + " 사기 당함");
        }
    }
}
