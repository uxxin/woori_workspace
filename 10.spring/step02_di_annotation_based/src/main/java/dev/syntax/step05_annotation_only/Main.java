package dev.syntax.step05_annotation_only;

import dev.syntax.step06_java_based.BeanConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context
                = new AnnotationConfigApplicationContext("dev.syntax.step05_annotation_only");
        // BeanConfiguration.java 파일을 빈 설정 정보가 작성된 설정 파일로 지정

        TapeReader reader = context.getBean(TapeReader.class);

        reader.test();
    }
}
