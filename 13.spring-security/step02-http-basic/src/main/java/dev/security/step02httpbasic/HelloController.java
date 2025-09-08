package dev.security.step02httpbasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(path="/hello")
    public String hello(){
        // 현재 상태에서는 Hello Security!! 라는 문자열도 인증이 되어야 접근할 수 있는 보호된 리소스
        return "Hello Security!!";
    }
}
