package dev.syntax.step07_java_based_with_scan;

public class TapeReader {

    private Tape tape;


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
