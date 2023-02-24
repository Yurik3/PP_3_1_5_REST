package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entity.MyUser;
import ru.kata.spring.boot_security.demo.repo.UserRepository;


import java.security.Principal;

@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String getUserInfo(Principal principal, Model model) {
        MyUser myUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", myUser);
        return "user";
    }
}
