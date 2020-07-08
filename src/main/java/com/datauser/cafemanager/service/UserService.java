package com.datauser.cafemanager.service;

import com.datauser.cafemanager.models.User;
import com.datauser.cafemanager.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	User findById(String id);

	User findByUserName(String userName);

	void createUser(CrmUser crmUser);

	List<User> findAll();
	List<User> findAllByEmailAndUserName(String userName, String email);

}
