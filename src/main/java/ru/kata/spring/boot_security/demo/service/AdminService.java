package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    public List<User> findAll();

    public Optional<User> findById(Long id);

    public void delete(User user);

    public void save(User user);

    User findByUsername(String username);
}
