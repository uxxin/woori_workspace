package dev.syntax.security.repository;

import dev.syntax.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // TODO: username에 해당하는 사용자를 조회하는 메서드
    Optional<User> findByUsername(String username);

}
