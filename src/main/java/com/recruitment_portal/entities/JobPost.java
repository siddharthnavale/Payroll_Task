package com.recruitment_portal.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Job_Post")
public class JobPost {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	private int postedBy;
	
	@Enumerated(EnumType.STRING)
	private JobType jobType;
	
	@ManyToOne
	private Company company;
	
	@CreationTimestamp
	private Date createdDate;
	
	private String jobDescription;
	
	private String jobRole;
	
	private String location;
	
	private boolean isActive=true;
	
	@OneToMany(mappedBy = "jobPost")
	private List<JobPostSkillSet> JobPostSkillSet;

	public JobPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPost(int id, int postedBy, JobType jobType, Company company, Date createdDate, String jobDescription,
			String jobRole, String location, boolean isActive,
			List<com.recruitment_portal.entities.JobPostSkillSet> jobPostSkillSet) {
		super();
		this.id = id;
		this.postedBy = postedBy;
		this.jobType = jobType;
		this.company = company;
		this.createdDate = createdDate;
		this.jobDescription = jobDescription;
		this.jobRole = jobRole;
		this.location = location;
		this.isActive = isActive;
		JobPostSkillSet = jobPostSkillSet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<JobPostSkillSet> getJobPostSkillSet() {
		return JobPostSkillSet;
	}

	public void setJobPostSkillSet(List<JobPostSkillSet> jobPostSkillSet) {
		JobPostSkillSet = jobPostSkillSet;
	}


}
