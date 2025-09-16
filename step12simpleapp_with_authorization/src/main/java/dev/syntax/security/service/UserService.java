package dev.syntax.security.service;

import dev.syntax.security.model.Role;
import dev.syntax.security.model.User;
import dev.syntax.security.repository.RoleRepository;
import dev.syntax.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService { // 별도의 인터페이스 없이 바로 작성

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository; //내가 추가

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

        // 기본 USER 역할 부여
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
        user.setRoles(Collections.singletonList(userRole));

        userRepository.save(user);
    }

    // 내가 추가함
    @Transactional
    public void addAdminRole(User user) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

        // 기존 역할 유지하면서 ADMIN 추가
        List<Role> roles = user.getRoles();
        if (!roles.contains(adminRole)) {
            roles.add(adminRole);
        }
        user.setRoles(roles);

        userRepository.save(user);
    }

    // 내가 추가: ID로 사용자 조회
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
