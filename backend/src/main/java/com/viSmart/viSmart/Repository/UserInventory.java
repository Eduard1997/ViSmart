package com.viSmart.viSmart.Repository;

import com.viSmart.viSmart.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInventory extends JpaRepository<User, Long>  {

    User findByEmail(String email);
    User findById(Integer userId);
    boolean existsByEmailAndPassword(String email,String password);
}
