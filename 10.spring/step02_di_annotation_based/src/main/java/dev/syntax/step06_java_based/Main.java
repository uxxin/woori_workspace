package dev.syntax.step06_java_based;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean을 구성하는 설정 정보(설정 파일)을 .java라는 파일을 활용하여 구성
 *
 * @Bean Annotation을 통해 개별 빈들을 .java 설정 파일에 작성
 *
 * 설정 파일이라고 하면, 보통 xml이나 .properties를 떠올릴 수 있지만,
 * 그냥 .java라는 파일도 설정 파일처럼 동작할 수 있음.
 */

public class Main {
    public static void main(String[] args) {

        var context
                = new AnnotationConfigApplicationContext(BeanConfiguration.class);

        TapeReader reader = context.getBean(TapeReader.class);

        reader.test();
    }
}
