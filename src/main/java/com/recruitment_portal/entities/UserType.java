package com.recruitment_portal.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_Type")
public class UserType {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	private String userTypeName;

	@OneToMany(mappedBy = "userType")
    private List<User> user;

    @OneToMany(mappedBy = "userType")
    private List<UserTypePermission> userTypePermission;

	public UserType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserType(String userTypeName, List<User> user, List<UserTypePermission> userTypePermission) {
		super();
		this.userTypeName = userTypeName;
		this.user = user;
		this.userTypePermission = userTypePermission;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<UserTypePermission> getUserTypePermission() {
		return userTypePermission;
	}

	public void setUserTypePermission(List<UserTypePermission> userTypePermission) {
		this.userTypePermission = userTypePermission;
	}
    
    
}
