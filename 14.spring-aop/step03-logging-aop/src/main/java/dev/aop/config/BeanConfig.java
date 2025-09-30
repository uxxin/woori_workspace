package dev.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "dev.aop")
@EnableAspectJAutoProxy // AOP가 프록시 객체를 생성하도록 자동으로 프록시 생성 옵션 활성화
public class BeanConfig {
}
