package dev.syntax.step03_xml_based_di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1. 스프링의 핵심 기술 중 하나인 의존성 주입을 활용하기 위해
 * spring-context.jar라는 의존성 설치
 *
 * 2. 스프링 컨테이너 구성 (TapeReaderFactory.java와 동일한 개념)
 * 스프링 컨테이너는 애플리케이션의 동작에 필요한 비즈니스 객체들을 관리하는 공간
 * 여기서는 Tape와 TapeReader 클래스들이 비즈니스 객체이자 빈(Bean)
 *
 * setter-config.xml이라는 설정 파일을 생성했음
 * (TapeReader라는 클래스와 Tape를 빈 객체로 정의했음)
 *
 * 3. 스프링 컨테이너를 통해 의존성을 주입해서 해결
 *  프로그램이 실행될 때,
 *  3-1. 스프링 컨테이너 객체를 생성하고 구성하기 위해 먼저 .xml을 참조
 *  -> new ClassPathXMLApplicationContext("setter-config.xml");
 *
 *  3-2. 설정 파일을 읽은 스프링 컨테이너를 통해 필요한 빈을 가져오기
 *  -> getBean(); // 의존성을 찾아서 해결
 */

public class Main {
    public static void main(String[] args) {
        // Before. Without Spring
        // 직접 팩토리 클래스를 구성하여 의존성을 해결
        // TapeReader reader = TapeReaderFactory.createTapeReader();

        // After. With Spring(컨테이너)
        // ApplicationContext - 스프링 컨테이너 객체

        // ClassPathXmlApplicationContext
        // -> "ClassPath의 XML파일을 기반으로 스프링 컨테이너를 구성하겠다."
        var context
                = new ClassPathXmlApplicationContext("setter-config.xml");

        // 스프링 컨테이너에서 TapeReader 의존성을 찾아서 초기화
        TapeReader reader = context.getBean(TapeReader.class);

//        Tape tape = new Tape("아일랜드", true);
//        reader.setTape(tape);
// 스프링 컨테이너를 통해 TapeReader를 생성하고,
// setter()를 통해 Tape 의존성도 해결됨

        reader.test();
        // 의존관계: TapeReader -> Tape


    }

}
