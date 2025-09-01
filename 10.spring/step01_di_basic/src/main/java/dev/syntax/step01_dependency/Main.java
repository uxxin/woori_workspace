package dev.syntax.step01_dependency;

// Tape와 TapeReader 클래스를 작성 후 테스트
/*
    Tape - 비디오 테이프
    TapeReader - 테이프 리더기
    
    TapeReader에 Tape를 넣고 테스트하는 프로그램
 */
public class Main {
    public static void main(String[] args) {
        TapeReader reader = new TapeReader();

        Tape tape = new Tape("아일랜드", true);

        // TapeReader는 Tape 타입에 의존하고 있음(dependent)
        // -> TapeReader.test()를 호출할 때 Tape 타입이 필요하기 때문에
        // setter를 통해 Tape 타입에 대한 의존성을 TapeReader에게 주입
        reader.setTape(tape);
        //-> TapeReader는 Tape가 없으면 동작할 수 없음

        // 테스트 수행
        reader.test();
    }
}
