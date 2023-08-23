package com.recruitment_portal.entities;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
public class User {

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private UserType userType;
	
	private String email;
	
	private String password;
	
	private LocalDate birthDate;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	private Boolean isActive = true;
	
	@Size(min = 10, max = 10)
	private String phoneNumber;
	
	private Boolean isEmailNotifiactionActive = true;
	
	@Lob
	private byte[] imageData;	
	
	@CreationTimestamp
	private Date registrationDate;
	
	@UpdateTimestamp
	private Date updatedAt;
	
	@OneToMany(mappedBy = "user")
	private List<UserLog> userLog;
	
	@OneToOne(mappedBy = "user")
	private JobSeekerProfile jobSeekerProfile;
	
	private Integer createdBy;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(UserType userType, String email, String password, LocalDate birthDate, GenderEnum gender,
			Boolean isActive, @Size(min = 10, max = 10) String phoneNumber, Boolean isEmailNotifiactionActive,
			byte[] imageData, Date registrationDate, Date updatedAt, List<UserLog> userLog,
			JobSeekerProfile jobSeekerProfile, int createdBy) {
		super();
		this.userType = userType;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.gender = gender;
		this.isActive = isActive;
		this.phoneNumber = phoneNumber;
		this.isEmailNotifiactionActive = isEmailNotifiactionActive;
		this.imageData = imageData;
		this.registrationDate = registrationDate;
		this.updatedAt = updatedAt;
		this.userLog = userLog;
		this.jobSeekerProfile = jobSeekerProfile;
		this.createdBy = createdBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getIsEmailNotifiactionActive() {
		return isEmailNotifiactionActive;
	}

	public void setIsEmailNotifiactionActive(Boolean isEmailNotifiactionActive) {
		this.isEmailNotifiactionActive = isEmailNotifiactionActive;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<UserLog> getUserLog() {
		return userLog;
	}

	public void setUserLog(List<UserLog> userLog) {
		this.userLog = userLog;
	}

	public JobSeekerProfile getJobSeekerProfile() {
		return jobSeekerProfile;
	}

	public void setJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
		this.jobSeekerProfile = jobSeekerProfile;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "{\"id\":" + id +",\"email\":\"" + email + "\",\"password\":\"" + password
				+ "\"}";
	}

	

}
