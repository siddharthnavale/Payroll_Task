package com.recruitment_portal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Job_Post_Skill_Set")
public class JobPostSkillSet {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private JobPost jobPost;
	
	private String skillSet;

	public JobPostSkillSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPostSkillSet(JobPost jobPost, String skillSet) {
		super();
		this.jobPost = jobPost;
		this.skillSet = skillSet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JobPost getJobPost() {
		return jobPost;
	}

	public void setJobPost(JobPost jobPost) {
		this.jobPost = jobPost;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	
	
	
}
