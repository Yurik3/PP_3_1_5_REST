package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Users;
import ru.kata.spring.boot_security.demo.repo.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImp implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImp(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public List<Users> findAll() {
        List <Users> users = (List<Users>) adminRepository.findAll();
        return users;
    }

    @Override
    public Optional<Users> findById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public void delete(Users users) {
        adminRepository.delete(users);

    }

    @Override
    public void save(Users users) {
        adminRepository.save(users);
    }

   public Users findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
