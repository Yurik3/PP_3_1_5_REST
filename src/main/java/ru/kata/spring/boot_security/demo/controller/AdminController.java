package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.MyUser;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repo.RoleRepository;
import ru.kata.spring.boot_security.demo.repo.UserRepository;
import ru.kata.spring.boot_security.demo.service.CustomUserDetailsService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AdminController {

    private CustomUserDetailsService customUserDetailsService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AdminController(CustomUserDetailsService customUserDetailsService, UserRepository userRepository, RoleRepository roleRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        Iterable<MyUser> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/addUser")
    public String addUser(Model model) {
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@ModelAttribute("user") MyUser user, @ModelAttribute("role") Role role) {
        roleRepository.save(role);
        userRepository.save(user);
        return "redirect:/admin";

    }

    @GetMapping("/admin/{id}/delete")
    public String deleteUser(@PathVariable(value = "id") long id, Model model) {
        MyUser user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(@PathVariable(value = "id") long id, Model model) {
        Optional<MyUser> user = userRepository.findById(id);
        ArrayList<MyUser> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "editUser";
    }

    @PostMapping("/admin/{id}/edit")
    public String editUser(@PathVariable(value = "id") long id, @ModelAttribute("user") MyUser user, @ModelAttribute("role") Role role) {
        roleRepository.save(role);
        userRepository.save(user);
        return "redirect:/admin";
    }
}
