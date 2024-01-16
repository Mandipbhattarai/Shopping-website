package com.springproject.shopping.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	String email;
	@Column(unique = true)
	private String phone;
	private String role;
	private String password;
	@Transient
	private String password2;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] profilePicture;
//	 defining a variable that should not be in the database to store converted
	// profile picture temporarily
	@Transient
    private String profilePictureBase64;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getProfilePictureBase64() {
		return profilePictureBase64;
	}

	public void setProfilePictureBase64(String profilePictureBase64) {
		this.profilePictureBase64 = profilePictureBase64;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	

}
