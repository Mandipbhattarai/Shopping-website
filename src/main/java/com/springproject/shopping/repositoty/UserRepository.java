package com.springproject.shopping.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.shopping.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByFirstName(String firstName);

	boolean existsByLastName(String lastName);

	boolean existsByPhone(String phone);
}
