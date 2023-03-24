package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.service.AdminService;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class AdminController {

    private AdminService adminService;
    private RoleService roleService;

    @Autowired
    public AdminController(AdminService adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUsers(Principal principal,Model model) {
        Iterable<User> users = adminService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("newUser",new User());
        model.addAttribute("roles",roleService.findAll());

        User user = adminService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        User admin = adminService.findByUsername(principal.getName());
        model.addAttribute("admin", admin);
        return "adminPanel";
    }


    @PostMapping("/admin")
    public String addUser(@ModelAttribute ("user") User user) {
        adminService.save(user);
        return "redirect:/admin";

    }

    @GetMapping("/admin/{id}")
    public String deleteUser(@PathVariable(value = "id") long id, Model model) {
        User user = adminService.findById(id).orElseThrow();
        adminService.delete(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(@PathVariable(value = "id") long id, Model model) {

        User userEdit = adminService.findById(id).orElseThrow();
        model.addAttribute("userEdit", userEdit);
        model.addAttribute("rolesEdit", roleService.findAll());

        return "redirect:/admin";
    }

    @PostMapping("/admin/{id}/edit")
    public String editUser(@PathVariable(value = "id") long id, @ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
        roleService.save(role);
        adminService.save(user);
        return "redirect:/admin";
    }
}
