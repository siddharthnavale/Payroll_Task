package com.recruitment_portal.Dto;

import java.util.ArrayList;
import java.util.List;

public class JwtResponseDto  {

	private String jwtToken;

	private ArrayList<String> permissions;

	private String email;

	private String  role;

	public JwtResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponseDto(String jwtToken, ArrayList<String> permissions, String email, String role) {
		super();
		this.jwtToken = jwtToken;
		this.permissions = permissions;
		this.email = email;
		this.role = role;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public ArrayList<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<String> permissions) {
		this.permissions = permissions;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}
