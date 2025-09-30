package dev.syntax.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //form login 정의 -login은 /custom/login에서 성공 시 /users/mypage로 logout은 /로
        http.formLogin().loginPage("/custom/login").defaultSuccessUrl("/users/mypage").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();

        // 메인 페이지(index.html), 회원가입 페이지는 별도의 인증, 권한 없이 접근 가능하도록 적용
        http.authorizeRequests().mvcMatchers("/","/users/register").permitAll();

        // 전체 사용자 목록을 조회하는 페이지(GET: users)는 ADMIN 역할을 가진 사용자만 접근 가능
        http.authorizeRequests().mvcMatchers(HttpMethod.GET,"/users").hasRole("ADMIN");

        // 회원가입은 누구나 가능해야 함.
        http.authorizeRequests().mvcMatchers(HttpMethod.POST,"/users").permitAll();

        // 그 외 다른 모든 경로는 인증된 사용자만 접근할 수 있도록 적용
        http.authorizeRequests().anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
