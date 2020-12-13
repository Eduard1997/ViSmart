package com.viSmart.viSmart.Repository;

import com.viSmart.viSmart.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInventory extends JpaRepository<User, Long>  {

    User findByEmail(String email);
    User findById(Integer userId);
    User findByUsername(String username);
    boolean existsByEmailAndPassword(String email,String password);
}
