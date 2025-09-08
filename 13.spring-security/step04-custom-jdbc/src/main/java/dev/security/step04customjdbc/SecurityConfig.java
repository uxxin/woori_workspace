package dev.security.step04customjdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

// 기본 구성 작성
// TODO: ???
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    // TODO: SecurityFilterChain만 빈으로 등록하는 코드

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityFilterChain 등록됨");

       // JDBC 기반 UserDetailsService 직접 생성
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        http
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                // UserDetailsService와 PasswordEncoder를 HttpSecurity에 직접 연결
                .userDetailsService(jdbcUserDetailsManager);

        return http.build();
    }

    //    1. 메모리 형태의 관리가 아닌, Database를 통해 관리하도록 구현
//
//    InMemoryUserDetailsManager가 아닌 JdbcUserDetailsManager를 활용하여 구현
//-> JdbcUserDetailsManager를 등록하는 과정에서 관련 의존성 필요할 수 있음
//    의존성(빈)을 직접 해결해보기


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PlainTextPasswordEncoder();
    }

//
//2. 패스워드 인코더 빈 등록
//
//    현재 DB에 저장된 비밀번호 데이터는 평문으로 저장되어 있음
//    따라서 평문으로 비밀번호를 비교해서 동작하는 패스워드 인코더가 필요
//
//    NoOpPasswordEncoder는 Deprecation 되었기 때문에 사용 불가(라고 가정)
//    직접 패스워드인코더 클래스를 커스터마이징해서 적용하기
//-> PlainTextPasswordEncoder.java 생성(Hint. PasswordEncoder 인터페이스를 구현)
//
//    해당 커스텀 인코더는 평문으로 비밀번호를 비교해서 동작하는 인코더
}

