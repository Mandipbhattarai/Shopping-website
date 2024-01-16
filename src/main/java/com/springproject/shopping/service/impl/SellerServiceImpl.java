package com.springproject.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springproject.shopping.model.Seller;
import com.springproject.shopping.repositoty.SellerRepository;
import com.springproject.shopping.service.SellerService;
@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerRepository sellerRepo;
	

	@Override
	public String sellerSignup(Seller seller) {
		
		if(sellerRepo.existsByEmail(seller.getEmail())) {
			return "Email";
		}
		if(sellerRepo.existsByPhone(seller.getPhone())) {
			return "Phone";
		}
		sellerRepo.save(seller);
		return null;
	}

	@Override
	public Seller findSellerByEmail(String email) {
		
		return sellerRepo.findByEmail(email);
	}

	@Override
	public List<Seller> getAllSeller() {
		
		return sellerRepo.findAll();
	}

	@Override
	public void deleteSeller(long id) {
		sellerRepo.deleteById(id);
		
	}

	@Override
	public boolean verifyPassword(String password, Seller seller, BCryptPasswordEncoder encoder) {
		if(seller !=null && encoder.matches(password, seller.getPassword())){
			return true;
		} else {
		return false;
	}
	}

}
