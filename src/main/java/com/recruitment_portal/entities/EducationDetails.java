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
@Table(name = "Education_Details")
public class EducationDetails {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private JobSeekerProfile jobSeekerProfile;
	
	private String degreeCertificateName;
	
	private String major;
	
	private String universityName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date completionDate;
	
    private double pecentage;

	public EducationDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EducationDetails(JobSeekerProfile jobSeekerProfile, String degreeCertificateName, String major,
			String universityName, Date startDate, Date completionDate, double pecentage) {
		super();
		this.jobSeekerProfile = jobSeekerProfile;
		this.degreeCertificateName = degreeCertificateName;
		this.major = major;
		this.universityName = universityName;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.pecentage = pecentage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JobSeekerProfile getJobSeekerProfile() {
		return jobSeekerProfile;
	}

	public void setJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
		this.jobSeekerProfile = jobSeekerProfile;
	}

	public String getDegreeCertificateName() {
		return degreeCertificateName;
	}

	public void setDegreeCertificateName(String degreeCertificateName) {
		this.degreeCertificateName = degreeCertificateName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public double getPecentage() {
		return pecentage;
	}

	public void setPecentage(double pecentage) {
		this.pecentage = pecentage;
	}
    
    
}
