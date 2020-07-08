package com.datauser.cafemanager.dao;

import com.datauser.cafemanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, String> {

    User findByUserName(String userName);
    
    User save(User user);

}
