package dev.syntax.security.controller;

import dev.syntax.security.model.User;
import dev.syntax.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 문제3. 전체 사용자 목록 조회 기능
    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();

        model.addAttribute("users", users);
        return "userList";
        //return null;
    }

    // 문제1. 사용자 회원가입 기능
    @PostMapping("/register")
    public String createUser(@ModelAttribute User user) {

        userService.createUser(user);
        return "redirect:/login";
    }

    // 문제2. 사용자 로그인이 성공할 경우 응답 처리를 수행할 핸들러
    @GetMapping("/profile")
    public String showMyPage(Authentication authentication, Model model) {
        // TODO: profile.html에 작성된 안녕하세요 {username}! 이 정상적으로 출력되도록 구현 ex) 안녕하세요 gugu!

        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());

        return "profile";
        // return profile 해야 하나 ?
    }
}
