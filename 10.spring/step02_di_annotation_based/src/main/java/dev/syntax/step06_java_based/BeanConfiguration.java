package dev.syntax.step06_java_based;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Bean 설정 정보를 담고 있는 Java 파일
// -> Bean에 대한 설정 정보를 해당 클래스 내에 정의
@Configuration
public class BeanConfiguration { // 8번 라인을 xml 파일과 비교하면 <beans> 부분
    // <bean>과 같이 개별 Bean들을 작성하는 부ㅜㅂㄴ

    @Bean //xml 파일로 비교하면 <bean>과 동일
    public TapeReader tapeReader(Tape tape) {
        // 빈을 생성하기 전에 별도의 로직을 작성할 수 있음.
        return new TapeReader(tape);
    }

    @Bean
    public Tape tape() {
        return new Tape("아일랜드", true);
    }
}
