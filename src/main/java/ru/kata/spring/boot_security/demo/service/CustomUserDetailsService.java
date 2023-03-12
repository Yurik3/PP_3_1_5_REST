package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminServiceImp adminServiceImp;

    @Autowired
    public CustomUserDetailsService(AdminServiceImp adminServiceImp) {
        this.adminServiceImp = adminServiceImp;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = adminServiceImp.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден: " + username);
        }
        return  user;
    }

}
