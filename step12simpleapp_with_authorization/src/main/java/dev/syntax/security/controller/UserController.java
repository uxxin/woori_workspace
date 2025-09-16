package dev.syntax.security.controller;

import dev.syntax.security.model.Product;
import dev.syntax.security.model.User;
import dev.syntax.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();

        model.addAttribute("users", users);
        return "userList";
    }

    // 여기 내가 void -> String으로 바꾸고 return 값 넣음.
    @PostMapping
    public String createUser(User user) {
        if (userService.getUser(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Already exists email with =" + user.getEmail());
        }

        userService.createUser(user);
        return "redirect:/custom/login";
    }

    @GetMapping("/mypage")
    public String showMyPage(Authentication authentication, Model model) {

        // 인증된 사용자의 정보를 담고 있는 객체에서 사용자 이름 취득
        User user = (User) authentication.getPrincipal();
        model.addAttribute("name", user.getName());

        return "myPage";
    }

    // 내가 작성: 관리자로 역할 변경
    @PostMapping("/{id}/make-admin")
    public String makeAdmin(@PathVariable Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userService.addAdminRole(user);
        return "redirect:/users";
    }
}
