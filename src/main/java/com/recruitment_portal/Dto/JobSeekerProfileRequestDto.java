package com.recruitment_portal.Dto;

import java.util.List;

import com.recruitment_portal.entities.EducationDetails;
import com.recruitment_portal.entities.ExperienceDetails;
import com.recruitment_portal.entities.SeekerSkillSet;

public class JobSeekerProfileRequestDto {

	private String firstName;
	
	private String lastName;
	
	private double currentSalaryPerAnnum;
	
	private List<EducationDetailsDto> educationDetails;
	
	private List<ExperienceDetailsDto> experienceDetails;
	
	private List<SeekerSkillSetDto> seekerSkillSet;

	public JobSeekerProfileRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobSeekerProfileRequestDto(String firstName, String lastName, double currentSalaryPerAnnum,
			List<EducationDetailsDto> educationDetails, List<ExperienceDetailsDto> experienceDetails,
			List<SeekerSkillSetDto> seekerSkillSet) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.currentSalaryPerAnnum = currentSalaryPerAnnum;
		this.educationDetails = educationDetails;
		this.experienceDetails = experienceDetails;
		this.seekerSkillSet = seekerSkillSet;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getCurrentSalaryPerAnnum() {
		return currentSalaryPerAnnum;
	}

	public void setCurrentSalaryPerAnnum(double currentSalaryPerAnnum) {
		this.currentSalaryPerAnnum = currentSalaryPerAnnum;
	}

	public List<EducationDetailsDto> getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(List<EducationDetailsDto> educationDetails) {
		this.educationDetails = educationDetails;
	}

	public List<ExperienceDetailsDto> getExperienceDetails() {
		return experienceDetails;
	}

	public void setExperienceDetails(List<ExperienceDetailsDto> experienceDetails) {
		this.experienceDetails = experienceDetails;
	}

	public List<SeekerSkillSetDto> getSeekerSkillSet() {
		return seekerSkillSet;
	}

	public void setSeekerSkillSet(List<SeekerSkillSetDto> seekerSkillSet) {
		this.seekerSkillSet = seekerSkillSet;
	}

	
}
