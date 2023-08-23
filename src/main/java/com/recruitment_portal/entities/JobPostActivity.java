package com.recruitment_portal.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Job_Post_Activity")
public class JobPostActivity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	private int appliedBy;
	
	@ManyToOne
	private JobPost jobPost; 
	
	@CreationTimestamp
	private Date appliedDate;

	public JobPostActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPostActivity(int appliedBy, JobPost jobPost, Date appliedDate) {
		super();
		this.appliedBy = appliedBy;
		this.jobPost = jobPost;
		this.appliedDate = appliedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(int appliedBy) {
		this.appliedBy = appliedBy;
	}

	public JobPost getJobPost() {
		return jobPost;
	}

	public void setJobPost(JobPost jobPost) {
		this.jobPost = jobPost;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	
	
}
