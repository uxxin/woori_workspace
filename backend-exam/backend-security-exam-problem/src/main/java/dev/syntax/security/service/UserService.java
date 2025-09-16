package dev.syntax.security.service;

import dev.syntax.security.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // 문제1. 사용자 회원 가입 기능
    void createUser(User user);

    Optional<User> getUser(String username);

    List<User> getUsers();
}
