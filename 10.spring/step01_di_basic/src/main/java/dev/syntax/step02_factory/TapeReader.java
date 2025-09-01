package dev.syntax.step02_factory;

public class TapeReader {
    private Tape tape;


    public void test() {
        if (tape.isWorked()) {
            System.out.println("tape.getName() = " + tape.getName() + " 정상 동작!");
        } else {
            System.out.println("tape.getName() = " + tape.getName() + " 사기 당함");
        }
    }


    public void setTape(Tape tape) {
        this.tape = tape;
    }
}
