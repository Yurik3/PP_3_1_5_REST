package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Users;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repo.RoleRepository;
import ru.kata.spring.boot_security.demo.service.AdminServiceImp;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class AdminController {

    private AdminServiceImp adminServiceImp;
    private RoleServiceImp roleServiceImp;

    @Autowired
    public AdminController(AdminServiceImp adminServiceImp, RoleServiceImp roleServiceImp) {
        this.adminServiceImp = adminServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        Iterable<Users> users = adminServiceImp.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/addUser")
    public String addUser(@ModelAttribute("user") Users user, Model model) {


        model.addAttribute("roles",roleServiceImp.findAll());
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@ModelAttribute ("user") Users users) {

        adminServiceImp.save(users);
        return "redirect:/admin";

    }

    @GetMapping("/admin/{id}/delete")
    public String deleteUser(@PathVariable(value = "id") long id, Model model) {
        Users users = adminServiceImp.findById(id).orElseThrow();
        adminServiceImp.delete(users);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(@PathVariable(value = "id") long id, Model model) {

        Users userEdit = adminServiceImp.findById(id).orElseThrow();
        model.addAttribute("userEdit", userEdit);
        model.addAttribute("rolesEdit", roleServiceImp.findAll());

        return "editUser";
    }

    @PostMapping("/admin/{id}/edit")
    public String editUser(@PathVariable(value = "id") long id, @ModelAttribute("user") Users users, @ModelAttribute("role") Role role) {
        roleServiceImp.save(role);
        adminServiceImp.save(users);
        return "redirect:/admin";
    }
}
