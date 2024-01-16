package com.springproject.shopping.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springproject.shopping.model.Seller;;

public interface SellerService {
	String sellerSignup(Seller seller);

	Seller findSellerByEmail(String email);

	List<Seller> getAllSeller();
	
	void deleteSeller(long id);

	boolean verifyPassword(String password, Seller seller, BCryptPasswordEncoder encoder);
	
}
