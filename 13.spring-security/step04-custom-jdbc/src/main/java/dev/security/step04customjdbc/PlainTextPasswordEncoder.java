package dev.security.step04customjdbc;

import org.springframework.security.crypto.password.PasswordEncoder;

// Spring Security에서 사용하는 빈으로 등록해야하기 때문에 PasswordEncoder를 구현
public class PlainTextPasswordEncoder implements PasswordEncoder {

    /**
     * 평문으로 전달받은 비밀번호를 암호화 처리하는 메서드
     * @param rawPassword - ex. "1234"
     * @return 인코딩된 값
     */
    @Override
    public String encode(CharSequence rawPassword) {
        // rawPassword - 사용자가 입력한 평문 비밀번호값
        return rawPassword.toString(); // 평문 그대로 다시 반환
    }

    /**
     * DB에 저장되어 있는 인코딩된 비밀번호 문자열 값이 사용자가 입력한 값과 일치하는지?
     * @param rawPassword - 사용자가 입력한 비밀번호 값
     * @param encodedPassword - DB에 저장되어 있던 실제 비밀번호 값(보통 인코딩된 값)
     * @return 비교 결과 일치 여부
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 비밀번호 비교 로직을 수행하는 부분
        return rawPassword.equals(encodedPassword);
    }
}
