package com.recruitment_portal.Dto;

import javax.validation.constraints.Pattern;

public class ForgotPasswordConfirmDto {

	String email;

	String otp;

	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,50}$",
            message = "Password must meet the following criteria:" +
                    " Minimum length should be 8 characters, " +
                    "At least 1 capital letter, " +
                    "At least 1 special character, " +
                    "At least 1 numeric character, ")
	String password;

	public ForgotPasswordConfirmDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgotPasswordConfirmDto(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
