package com.datauser.cafemanager.service.impl;
import com.datauser.cafemanager.dao.UserDao;
import com.datauser.cafemanager.models.User;
import com.datauser.cafemanager.service.UserService;
import com.datauser.cafemanager.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	public void createUser(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setEmail(crmUser.getEmail());
		user.setUserRole(crmUser.getUserRole());

		 // save user in the database
		userDao.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(List.of(user.getUserRole())));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles) {
		return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(String id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public List<User> findAllByEmailAndUserName(String userName, String email) {
		return userDao.findAllByEmailAndUserName(userName, email);
	}
}
