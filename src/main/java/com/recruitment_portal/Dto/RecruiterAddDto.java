package com.recruitment_portal.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class RecruiterAddDto {

	private String dateOfBirth;

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String email;

	private String gender;

	@Size(min = 10, max = 10)
	private String phoneNumber;

	public RecruiterAddDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecruiterAddDto(String dateOfBirth, @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}") String email,
			String gender, @Size(min = 10, max = 10) String phoneNumber) {
		super();
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
