package dev.security.step03formlogin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityFilterChain 등록됨");

        // http.httpBasic(Customizer.withDefaults());

        // Form 로그인 방식
        http.formLogin(Customizer.withDefaults());

        http.authorizeHttpRequests(auth -> { // 요청에 대해서 인가를 수행해라
            auth
                    .anyRequest()
                    .authenticated();
        });

        return http.build();
    }

    // 시큐리티가 기본으로 사용하던 UserDetailsService(빈)를 개발자가 재정의해서 사용
    @Bean
    public UserDetailsService userDetailsService() {
        // 임시로 서버 실행 기간 동안만 유지시킬 수 있도록 메모리 형태로 사용자를 관리하는 객체 생성
        var userDetailsService = new InMemoryUserDetailsManager();
        // 자동생성 계정이 아닌, 직접 사용할 User 객체를 등록하기 위해
        UserDetails testUser = User.withUsername("ujin")
                .password("1234")
                .authorities("read") // 지금은 필요 없음(동작 과정에서 에러 방지)
                .build();

        // User를 관리하는 userDetailService에게 testUser를 관리하도록 추가
        userDetailsService.createUser(testUser);

        // 직접 생성한 커스텀 User 클래스도 userDetailsService에서 관리하도록 추가
        SimpleUser testUser2 = new SimpleUser("ujin2", "1234");
        userDetailsService.createUser(testUser2);

        return userDetailsService;
    }

    // 별도의 PasswordEncoder(빈)을 등록하지 않고 서버를 실행해서 로그인하면
    // 다음과 같은 예외 발생: Given that there is no default password encoder configured, each password must have a password encoding prefix.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder - 별도의 암호화나 해시를 적용하지 않고, 일반 텍스트처럼 처리
        // 운영 단계에서는 (절대) 이용하면 안됨
        return NoOpPasswordEncoder.getInstance();
    }
}

