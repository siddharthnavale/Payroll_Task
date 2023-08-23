package com.recruitment_portal.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user_Log")
public class UserLog {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date loginDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date jobApplyDate;

	public UserLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLog(User user, Date loginDate, Date jobApplyDate) {
		super();
		this.user = user;
		this.loginDate = loginDate;
		this.jobApplyDate = jobApplyDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getJobApplyDate() {
		return jobApplyDate;
	}

	public void setJobApplyDate(Date jobApplyDate) {
		this.jobApplyDate = jobApplyDate;
	}
	
	
	
}
