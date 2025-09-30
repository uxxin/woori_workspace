package dev.syntax.step03_xml_based_di;

public class TapeReader {
    private Tape tape;


    public void test() {
        if (tape.isWorked()) {
            System.out.println("tape.getName() = " + tape.getName() + " 정상 동작!");
        } else {
            System.out.println("tape.getName() = " + tape.getName() + " 사기 당함");
        }
    }

    // <tape>이 잘 있어야 초기화가 됨. -> 이걸 spring이 해줄 수 있도록(spring이 new Tape(); 해줌)
    public void setTape(Tape tape) {;
        System.out.println("tape = " + tape);
        System.out.println("setTape() 호출됨");
        this.tape = tape;
    }
}
