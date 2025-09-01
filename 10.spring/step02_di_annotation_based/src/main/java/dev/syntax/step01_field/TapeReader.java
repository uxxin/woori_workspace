package dev.syntax.step01_field;

import org.springframework.beans.factory.annotation.Autowired;

public class TapeReader {

    // 필드 기반으로 의존성 주입을 적용하는 에시 ( setter 기반으로 하고 싶으면 setter 위에 작성하면 됨 )
    @Autowired
    private Tape tape;

    // 생성자
    public TapeReader(Tape tape) {

        System.out.println("TapeReader() 기본 생성자 호출됨");
    }

    public void test() {
        if (tape.isWorked()) {
            System.out.println("tape.getName() = " + tape.getName() + " 정상 동작!");
        } else {
            System.out.println("tape.getName() = " + tape.getName() + " 사기 당함");
        }
    }

    // <tape>이 잘 있어야 초기화가 됨. -> 이걸 spring이 해줄 수 있도록 XML에서 설정(spring이 new Tape(); 해줌)
    public void setTape(Tape tape) {;
        System.out.println("tape = " + tape);
        System.out.println("setTape() 호출됨");
        this.tape = tape;
    }
}
