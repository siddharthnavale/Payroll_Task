package com.recruitment_portal.Dto;

public class JobPostResponseDto {

	private String appliedFor;
	
	private String AlreadyAppliedFor;

	public JobPostResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPostResponseDto(String appliedFor, String alreadyAppliedFor) {
		super();
		this.appliedFor = appliedFor;
		AlreadyAppliedFor = alreadyAppliedFor;
	}

	public String getAppliedFor() {
		return appliedFor;
	}

	public void setAppliedFor(String appliedFor) {
		this.appliedFor = appliedFor;
	}

	public String getAlreadyAppliedFor() {
		return AlreadyAppliedFor;
	}

	public void setAlreadyAppliedFor(String alreadyAppliedFor) {
		AlreadyAppliedFor = alreadyAppliedFor;
	}
	
	
}
