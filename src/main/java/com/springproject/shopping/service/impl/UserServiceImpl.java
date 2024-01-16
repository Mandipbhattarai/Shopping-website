package com.springproject.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springproject.shopping.model.User;
import com.springproject.shopping.repositoty.UserRepository;
import com.springproject.shopping.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public String userSignup(User user) {

		if (userRepo.existsByEmail(user.getEmail())) {
			return "Email";
		}
		if (userRepo.existsByPhone(user.getPhone())) {
			return "Phone";
		}

		// this saves the data to the database
		userRepo.save(user);
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> getAllUser() {

		return userRepo.findAll();
	}

	@Override
	public void deleteUser(long id) {
		userRepo.deleteById(id);

	}
	@Override
	public boolean verifyPassword(String password, User user, BCryptPasswordEncoder encoder ) {
		if (user != null && encoder.matches(password, user.getPassword())) {
			return true;
		} else {
		return false;
		}
	}

	

	

}
