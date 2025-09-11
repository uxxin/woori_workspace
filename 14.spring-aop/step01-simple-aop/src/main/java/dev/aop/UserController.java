package dev.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserController {

    Logger logger = Logger.getLogger("UserController");

    public List<User> getUsers() {
        //logger.info("UserController.getUser() 호출 전");
        // DB로부터 User 목록을 받았다고 가정
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Tom"));
        users.add(new User(2, "Jerry"));
        //logger.info("UserController.getUser() 호출 후");

        return users;
    }
}