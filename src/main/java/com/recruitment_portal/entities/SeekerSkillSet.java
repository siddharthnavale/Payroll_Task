package com.recruitment_portal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Seeker_Skill_Set")
public class SeekerSkillSet {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne 
	private JobSeekerProfile jobSeekerProfile;

	private String skillSet;
	
	private int skillSetLevel;

	public SeekerSkillSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeekerSkillSet(JobSeekerProfile jobSeekerProfile, String skillSet, int skillSetLevel) {
		super();
		this.jobSeekerProfile = jobSeekerProfile;
		this.skillSet = skillSet;
		this.skillSetLevel = skillSetLevel;
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

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public int getSkillSetLevel() {
		return skillSetLevel;
	}

	public void setSkillSetLevel(int skillSetLevel) {
		this.skillSetLevel = skillSetLevel;
	}
	
	
}
