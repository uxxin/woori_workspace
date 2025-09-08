package dev.security.step02httpbasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 해당 파일은 Security와 관련된 빈 객체를 설정하는 설정 파일임을 명시
@EnableWebSecurity // 시큐리티와 관련된 설정들을 활성화하는 옵션
public class SecurityConfig {

    // 시큐리티와 관련된 필요한 @Bean들을 등록하면 됨
    // Q. 하는 이유?
    // 시큐리티의 동작과 관련해서 커스터마이징이 필요한 스프링 빈들을 직접 작성, 등록하기 위해

    // 1. SecurityFilterChain이라는 스프링 시큐리티의 빈을 재정의
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityFilterChain 등록됨");
        // TODO: 개발자가 원하는 방식으로 설정 코드를 작성하면, 기본값이 아니라 그 코드로 동작

        // 현재 시큐리티의 기본 옵션은 Form 로그인 방식으로 동작하고 있음.
        http.httpBasic(Customizer.withDefaults());
        // -> 기본값이었던 Form 로그인 방식을 비활성화, HTTP Basic 방식으로 활성화

        // 어떤 엔드포인트 경로로 요청이 왔을 떄 인증을 거치게 할 것인지?
        http.authorizeHttpRequests()// 요청에 대해서 인가를 수행해라
                .anyRequest() // 모든 경로의 요청에 대해(any)
                .authenticated(); // 인증된 사용자만 접근할 수 있어야 한다.

        return http.build();
    }

}
