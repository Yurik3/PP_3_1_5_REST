package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    public void save(Role role);

    public Optional<Role> findById(Long id) ;

    public List<Role> findAll();
}
