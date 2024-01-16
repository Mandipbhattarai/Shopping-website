package com.springproject.shopping.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "seller_tbl")
public class Seller {
	@Id
	@GeneratedValue
	private long id;
	private String companyName;
	private String ownerName;
	private String ownerPhoneNo;
	private String ownerEmail;
	private String email;
	private String phone;
	private String password;
	@Transient
	private String password2;
	private byte[] businessVerificationDocument;
	private byte[] panCertificate;
	private String address;
	//this connects the product table to seller table
	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();
	@OneToMany(mappedBy="seller",cascade = CascadeType.ALL)
	private List<Bank> bankdetails = new ArrayList<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerPhoneNo() {
		return ownerPhoneNo;
	}
	public void setOwnerPhoneNo(String ownerPhoneNo) {
		this.ownerPhoneNo = ownerPhoneNo;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public byte[] getBusinessVerificationDocument() {
		return businessVerificationDocument;
	}
	public void setBusinessVerificationDocument(byte[] businessVerificationDocument) {
		this.businessVerificationDocument = businessVerificationDocument;
	}
	public byte[] getPanCertificate() {
		return panCertificate;
	}
	public void setPanCertificate(byte[] panCertificate) {
		this.panCertificate = panCertificate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Bank> getBankdetails() {
		return bankdetails;
	}
	public void setBankdetails(List<Bank> bankdetails) {
		this.bankdetails = bankdetails;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
}
