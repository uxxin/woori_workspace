package dev.syntax.security;

import dev.syntax.security.model.User;
import dev.syntax.security.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
// 문제 2. 사용자(Users) 로그인 기능 테스트 시나리오
public class Ex2Tests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("올바른 ID/PW로 요청 시 profile.html 페이지로 302 리다이렉트된다.")
    public void test1() throws Exception {
        mockMvc.perform(formLogin().user("gugu").password("1111"))
                .andExpect(status().isFound()) // HTTP Status가 302 redirect로 응답하는지?
                .andExpect(redirectedUrl("/users/profile")); // /users/profile로 리다이렉트 하는지?
    }

    @Test
    @DisplayName("유효하지 않은 ID/PW로 요청 시 다시 로그인 페이지로 302 리다이렉트된다.")
    public void test2() throws Exception {
        mockMvc.perform(formLogin().user("anonymous").password("2222"))
                .andExpect(status().isFound()) // HTTP Status가 302 redirect로 응답하는지?
                .andExpect(redirectedUrl("/login?error")); // /users/profile로 리다이렉트 하는지?
    }

    @Test
    @DisplayName("gugu 계정으로 로그인하였을 경우, profile.html에 표시될 값으로 username=gugu 라는 값이 존재하는지 테스트")
    public void test3() throws Exception {
        // 로그인 요청
        mockMvc.perform(formLogin().user("gugu").password("1111"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/users/profile")); // 로그인 성공 후 profile 페이지로 리다이렉트 확인

        // 리다이렉트된 페이지 요청 후 모델 속성 검사
        mockMvc.perform(get("/users/profile").with(user(new User("gugu", "1111"))))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("username"))
                .andExpect(model().attribute("username", "gugu"));
    }



}
