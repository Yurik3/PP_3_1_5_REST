package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repo.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImp implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImp(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public List<User> findAll() {
        List <User> users = (List<User>) adminRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public void delete(User user) {
        adminRepository.delete(user);

    }

    @Override
    public void save(User user) {
        adminRepository.save(user);
    }
    @Override
    public void update(Long id, User updateUser) {
        updateUser.setId(id);
        adminRepository.save(updateUser);
    }

   public User findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
