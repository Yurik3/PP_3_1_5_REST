package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repo.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);

    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }


    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
