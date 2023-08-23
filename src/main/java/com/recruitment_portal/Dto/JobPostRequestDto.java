package com.recruitment_portal.Dto;

import java.util.ArrayList;

public class JobPostRequestDto {

	private String jobType;
	
	private int companyId;
	
	private String jobDescription;
	
	private String jobRole;
	
	private String location;
	
	private ArrayList<String> skillSetRequiredForJob;

	public JobPostRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPostRequestDto(String jobType, int companyId, String jobDescription, String jobRole, String location,
			ArrayList<String> skillSetRequiredForJob) {
		super();
		this.jobType = jobType;
		this.companyId = companyId;
		this.jobDescription = jobDescription;
		this.jobRole = jobRole;
		this.location = location;
		this.skillSetRequiredForJob = skillSetRequiredForJob;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public ArrayList<String> getSkillSetRequiredForJob() {
		return skillSetRequiredForJob;
	}

	public void setSkillSetRequiredForJob(ArrayList<String> skillSetRequiredForJob) {
		this.skillSetRequiredForJob = skillSetRequiredForJob;
	}


	
}
