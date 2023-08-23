package com.recruitment_portal.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String actionName;
	
	private String description;
	
	private String method;
	
	private String baseUrl;
	
	private boolean isActive = true;
	
	@OneToMany(mappedBy = "permission")
	private List<UserTypePermission> userTypePermission;

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permission(String actionName, String description, String method, String baseUrl, boolean isActive,
			List<UserTypePermission> userTypePermission) {
		super();
		this.actionName = actionName;
		this.description = description;
		this.method = method;
		this.baseUrl = baseUrl;
		this.isActive = isActive;
		this.userTypePermission = userTypePermission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<UserTypePermission> getUserTypePermission() {
		return userTypePermission;
	}

	public void setUserTypePermission(List<UserTypePermission> userTypePermission) {
		this.userTypePermission = userTypePermission;
	} 
	
	

}
