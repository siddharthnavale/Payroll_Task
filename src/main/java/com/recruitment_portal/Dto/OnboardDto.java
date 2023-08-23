package com.recruitment_portal.Dto;

import java.util.UUID;

import javax.validation.constraints.Pattern;

public class OnboardDto {

	private UUID uuid;
	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,50}$",
            message = "Password must meet the following criteria:" +
                    " Minimum length should be 8 characters, " +
                    "At least 1 capital letter, " +
                    "At least 1 special character, " +
                    "At least 1 numeric character, ")
	private String password;

	private String confirmPassword;

	public OnboardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OnboardDto(UUID uuid,
			@Pattern(regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,50}$", message = "Password must meet the following criteria: Minimum length should be 8 characters, At least 1 capital letter, At least 1 special character, At least 1 numeric character, ") String password,
			String confirmPassword) {
		super();
		this.uuid = uuid;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	

}
