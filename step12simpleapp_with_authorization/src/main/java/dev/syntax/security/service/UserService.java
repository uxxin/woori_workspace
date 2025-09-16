package dev.syntax.security.service;

import dev.syntax.security.model.User;
import dev.syntax.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService { // 별도의 인터페이스 없이 바로 작성

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void createUser(User user) {
        
        // 패스워드 인코딩 처리
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.encodePassword(encodedPassword);

        userRepository.save(user);
    }
}
