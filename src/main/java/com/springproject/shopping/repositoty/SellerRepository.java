package com.springproject.shopping.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.shopping.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
	Seller findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);
}
