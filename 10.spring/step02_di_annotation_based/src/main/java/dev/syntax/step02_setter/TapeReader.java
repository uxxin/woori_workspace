package dev.syntax.step02_setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


public class TapeReader {

    private Tape tape;

//    // 생성자
//    public TapeReader(Tape tape) {
//        System.out.println("TapeReader() 기본 생성자 호출됨");
//    }

    public void test() {
        if (tape.isWorked()) {
            System.out.println("tape.getName() = " + tape.getName() + " 정상 동작!");
        } else {
            System.out.println("tape.getName() = " + tape.getName() + " 사기 당함");
        }
    }


    @Autowired
    public void setTape(Tape tape) {;
        System.out.println("tape = " + tape);
        System.out.println("setTape() 호출됨");
        this.tape = tape;
    }
}
