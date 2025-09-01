package dev.syntax.step04_constructor_based_di;

public class TapeReader {
    private Tape tape;

    // 생성자 
    public TapeReader(Tape tape) {
        this.tape = tape;
        System.out.println("tape = " + tape);
        System.out.println("생성자 호출됨");
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
