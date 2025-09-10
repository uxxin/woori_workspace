package dev.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // 보호된 리소스라고 가정
    @GetMapping("/hello")
    public String hello() {
        return "hello!"; 
    }
}
