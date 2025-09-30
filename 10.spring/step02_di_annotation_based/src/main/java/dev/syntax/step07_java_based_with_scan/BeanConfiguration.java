package dev.syntax.step07_java_based_with_scan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@ComponentScan(basePackages = "dev.syntax.step07_java_based_with_scan")
// -> 설정 파일의 클래스 레벨에 컴포넌트 스캔 대상이 될 기본 패키지 경로 명시
public class BeanConfiguration {

    @Bean //xml 파일로 비교하면 <bean>과 동일
    public TapeReader tapeReader(Tape tape) {
        // 빈 생성 전에 별도의 로직이 필요하기 때문에
        // 해당 클래스 레벨에 @Component를 사용하지 않고, @Bean을 통해 생성해야 하는 상호아


        // 빈 생성 전 별도의 로직..
        return new TapeReader(tape);
    }
    // Tape 클래스는 빈 생성 전 별도의 로직이 따로 없기 때문에
    // 일반적인 컴포넌트 스캔으로 생성되도록 적용하고 싶을 경우 ?
//    @Bean
//    public Tape tape() {
//        return new Tape("아일랜드", true);
//    }
}
