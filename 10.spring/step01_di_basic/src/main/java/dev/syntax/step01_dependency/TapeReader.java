package dev.syntax.step01_dependency;

// Tape를 테스트할 수 있는 비디오 리더기
public class TapeReader {
    private Tape tape;

    // 비디오 테이프가 잘 동작하는지 테스트해주는 메서드
    public void test() {
        if (tape.isWorked()) {
            System.out.println("tape.getName() = " + tape.getName() + " 정상 동작!");
        } else {
            System.out.println("tape.getName() = " + tape.getName() + " 사기 당함");
        }
    }

    // 리더기에 테이프를 세팅하는 메서드
    public void setTape(Tape tape) {
        this.tape = tape;
    }
}
