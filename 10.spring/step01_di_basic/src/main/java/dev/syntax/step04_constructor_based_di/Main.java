package dev.syntax.step04_constructor_based_di;

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

        var context
                = new ClassPathXmlApplicationContext("constructor-config.xml");

        TapeReader reader = context.getBean(TapeReader.class);

        reader.test();
    }
}
