package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.exception.NoSuchUserEx;
import ru.kata.spring.boot_security.demo.exception.UserIncorrect;
import ru.kata.spring.boot_security.demo.service.AdminService;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminRestController {
    private AdminService adminService;

    @Autowired
    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public List<User> showAllUser() {

        List<User> allUser = adminService.findAll();
        return allUser;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {

        User user = adminService.findById(id).orElseThrow();
        return user;
    }


    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        adminService.save(user);
        return ResponseEntity.ok(user);
    }


    @PatchMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable(value = "id") long id, @RequestBody User user) {
        adminService.update(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long id) {
        User user = adminService.findById(id).orElseThrow();
        adminService.delete(user);
        return ResponseEntity.ok(user);
    }

}
