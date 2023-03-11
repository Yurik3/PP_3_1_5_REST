package ru.kata.spring.boot_security.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Users;

@Repository
public interface AdminRepository extends JpaRepository<Users, Long> {
   public Users findByUsername(String username);

}
