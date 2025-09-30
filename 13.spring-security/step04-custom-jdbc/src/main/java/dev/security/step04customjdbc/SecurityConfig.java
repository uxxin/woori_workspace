package dev.security.step04customjdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

// 기본 구성 작성
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults());

        http.authorizeHttpRequests(auth -> {
            auth
                    .anyRequest()
                    .authenticated();
        });

        return http.build();
    }

    // userDetailsService는 dataSource에 의존하고 있다.
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        // JDBC를 기반으로 DB에서 사용자를 조회할 수 있도록 구현체를 지정
        var userDetailsService = new JdbcUserDetailsManager(dataSource);

        return userDetailsService;
    }

    // 현재 DB에 비밀번호는 평문으로 저장이 되어 있음
    // 별도의 암호화 없이 그냥 평문으로 비밀번호를 검증할 수 있는 커스텀 인코더 객체를 만들고 빈으로 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 직접 구현한 인코더를 스프링 시큐리티의 인증 메커니즘 과정에 활용될 수 있도록 빈으로 등록
        return new PlainTextPasswordEncoder();
    }
}
