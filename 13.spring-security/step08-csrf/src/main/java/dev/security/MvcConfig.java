package dev.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 스프링 MVC 관련 설정 파일
// DispatcherServlet과 관련된 매핑 처리 수행
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    // 별도의 컨트롤러를 작성하지 않고, 코드레벨에서 처리

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        // HomeController의 @GetMapping("/home") {...}와 동일하게 동작
    }
}

