package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repo.AdminRepository;


import java.security.Principal;

@Controller
public class UserController {

    private AdminRepository adminRepository;

    @Autowired
    public UserController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/user")
    public String getUserInfo(Principal principal, Model model) {
        User user = adminRepository.findByUsername(principal.getName());
        model.addAttribute("users", user);
        return "user";
    }
}
