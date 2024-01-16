package com.springproject.shopping.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springproject.shopping.model.User;

public interface UserService {
	String userSignup(User user);

	User findUserByEmail(String email);

	List<User> getAllUser();
	
	void deleteUser(long id);

	boolean verifyPassword(String password, User user, BCryptPasswordEncoder encoder);
	
	
}
