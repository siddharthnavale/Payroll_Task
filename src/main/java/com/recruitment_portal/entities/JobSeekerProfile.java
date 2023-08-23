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
@Table(name = "Job_Seeker_Profile")
public class JobSeekerProfile {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private User user;
	
	private String firstName;
	
	private String lastName;
	
	private Double currentSalaryPerAnnum;
	
	@OneToMany(mappedBy = "jobSeekerProfile")
	private List<EducationDetails> educationDetails;
	
	@OneToMany(mappedBy = "jobSeekerProfile")
	private List<ExperienceDetails> experienceDetails;
	
	@OneToMany(mappedBy = "jobSeekerProfile")
	private List<SeekerSkillSet> seekerSkillSet;

	public JobSeekerProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobSeekerProfile(User user, String firstName, String lastName, Double currentSalaryPerAnnum,
			List<EducationDetails> educationDetails, List<ExperienceDetails> experienceDetails,
			List<SeekerSkillSet> seekerSkillSet) {
		super();
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.currentSalaryPerAnnum = currentSalaryPerAnnum;
		this.educationDetails = educationDetails;
		this.experienceDetails = experienceDetails;
		this.seekerSkillSet = seekerSkillSet;
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

	public Double getCurrentSalaryPerAnnum() {
		return currentSalaryPerAnnum;
	}

	public void setCurrentSalaryPerAnnum(Double currentSalaryPerAnnum) {
		this.currentSalaryPerAnnum = currentSalaryPerAnnum;
	}

	public List<EducationDetails> getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(List<EducationDetails> educationDetails) {
		this.educationDetails = educationDetails;
	}

	public List<ExperienceDetails> getExperienceDetails() {
		return experienceDetails;
	}

	public void setExperienceDetails(List<ExperienceDetails> experienceDetails) {
		this.experienceDetails = experienceDetails;
	}

	public List<SeekerSkillSet> getSeekerSkillSet() {
		return seekerSkillSet;
	}

	public void setSeekerSkillSet(List<SeekerSkillSet> seekerSkillSet) {
		this.seekerSkillSet = seekerSkillSet;
	}
	
	
}
