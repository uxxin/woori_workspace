package dev.syntax.security.repository;

import dev.syntax.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Optional<User> findUserByUsername(String username); // findByEmail로 변경
    Optional<User> findByEmail(String email);
}
