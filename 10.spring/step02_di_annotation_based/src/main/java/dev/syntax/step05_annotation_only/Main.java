package dev.syntax.step05_annotation_only;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
    별도의 XML 파일 없이, Annotation 만으로 빈을 등록하고, 스프링 컨테이너 구성

    이제는 스프링 컨테이너를 의미하는 Context 객체가 바뀜

    ClassPathXmlApplicationContext ->  AnnotationConfigApplicationContext

    파라미터의 인수로 컴포넌트 스캔 대상이 될 기본 패키지 경로를 명시해주면 됨.
 */

public class Main {
    public static void main(String[] args) {

        var context
                = new AnnotationConfigApplicationContext("dev.syntax.step05_annotation_only");

        TapeReader reader = context.getBean(TapeReader.class);

        reader.test();
    }
}
