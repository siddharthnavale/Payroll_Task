package com.recruitment_portal.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDtoRequest {
	
	private String dateOfBirth;
	
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String email;
	
	private String gender;
	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,50}$",
            message = "Password must meet the following criteria:" +
                    " Minimum length should be 8 characters, " +
                    "At least 1 capital letter, " +
                    "At least 1 special character, " +
                    "At least 1 numeric character, ")
	private String password;
	
	@Size(min=10,max=10)
	private String phoneNumber;
	
	@Pattern(regexp = "^[12]$",
            message = "Eighter Admin or Job Seeker, Other user type not allwed")
	private String userType;

	public UserDtoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDtoRequest(String dateOfBirth, @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}") String email,
			String gender,
			@Pattern(regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,50}$", message = "Password must meet the following criteria: Minimum length should be 8 characters, At least 1 capital letter, At least 1 special character, At least 1 numeric character, ") String password,
			@Size(min = 10, max = 10) String phoneNumber,
			@Pattern(regexp = "^[12]$", message = "Eighter Admin or Job Seeker, Other user type not allwed") String userType) {
		super();
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	
}
