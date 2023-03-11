package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Users;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    public List<Users> findAll();

    public Optional<Users> findById(Long id);

    public void delete(Users users);

    public void save(Users users);

    Users findByUsername(String username);
}
