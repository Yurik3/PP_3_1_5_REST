package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.service.AdminService;
import ru.kata.spring.boot_security.demo.service.AdminServiceImp;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;

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
    public String getUsers(Model model) {
        Iterable<User> users = adminService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/addUser")
    public String addUser(@ModelAttribute("user") User user, Model model) {


        model.addAttribute("roles",roleService.findAll());
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@ModelAttribute ("user") User user) {

        adminService.save(user);
        return "redirect:/admin";

    }

    @GetMapping("/admin/{id}/delete")
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

        return "editUser";
    }

    @PostMapping("/admin/{id}/edit")
    public String editUser(@PathVariable(value = "id") long id, @ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
        roleService.save(role);
        adminService.save(user);
        return "redirect:/admin";
    }
}
