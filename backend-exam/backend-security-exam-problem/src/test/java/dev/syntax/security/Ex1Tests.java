package dev.syntax.security;

import dev.syntax.security.model.User;
import dev.syntax.security.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
// 문제 1. 사용자(Users) 회원가입 기능 테스트 시나리오
public class Ex1Tests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 시 입력한 비밀번호(ex. 1234)는 DB에 인코딩되어 저장된다.")
    public void testPasswordIsEncrypted2() throws Exception {
        // Given, 테스트용 목 객체 생성
        String username = "yuyu";
        String rawPassword = "1234";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User mockUser = new User(username, encodedPassword);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);

        // When, 회원가입 API 호출 (POST 요청)
        mvc.perform(post("/users/register")
                        .param("username", username)
                        .param("password", rawPassword)
                        .with(csrf()) // CSRF 우회
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        // Then, save()가 호출되었는지 검증
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userCaptor.capture()); // 모킹된 User 객체 캡처

        User savedUser = userCaptor.getValue(); // 캡처된 객체에서 값 추출
        System.out.println("Encoded Password: " + savedUser.getPassword());

        // 비밀번호 암호화 여부 검증
        assertNotEquals(rawPassword, savedUser.getPassword()); // 평문과 비교하여 비밀번호가 암호화되었는지 확인
        assertTrue(passwordEncoder.matches(rawPassword, savedUser.getPassword())); // matches()로 확인
    }
}
