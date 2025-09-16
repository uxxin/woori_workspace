package dev.syntax.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 스프링 시큐리티에서 사용할 스프링 빈을 설정하는 빈 설정 정보 파일
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: 설정 정보 작성
        http.authorizeRequests().mvcMatchers("/","/users/register").permitAll();
        http.authorizeRequests().mvcMatchers(HttpMethod.POST,"/users/register").permitAll();

        http.formLogin().loginPage("/login").defaultSuccessUrl("/users/profile").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();

        http.authorizeRequests().mvcMatchers(HttpMethod.GET,"/users").hasRole("ADMIN");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}