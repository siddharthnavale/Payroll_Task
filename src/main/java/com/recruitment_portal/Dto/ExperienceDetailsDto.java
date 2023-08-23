package com.recruitment_portal.Dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ExperienceDetailsDto {

	private String companyName;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date endDate;
	
	private boolean isCurrentJob;
	
	private String jobTitle;
	
	private String roleDescription;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date startDate;

	public ExperienceDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExperienceDetailsDto(String companyName, Date endDate, boolean isCurrentJob, String jobTitle,
			String roleDescription, Date startDate) {
		super();
		this.companyName = companyName;
		this.endDate = endDate;
		this.isCurrentJob = isCurrentJob;
		this.jobTitle = jobTitle;
		this.roleDescription = roleDescription;
		this.startDate = startDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isCurrentJob() {
		return isCurrentJob;
	}

	public void setCurrentJob(boolean isCurrentJob) {
		this.isCurrentJob = isCurrentJob;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	
}
