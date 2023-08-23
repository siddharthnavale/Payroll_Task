package com.recruitment_portal.Dto;

import java.util.ArrayList;
import java.util.HashSet;

public class JobPostsDto {
	
	private ArrayList<Integer> jobPost;

	public JobPostsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPostsDto(ArrayList<Integer> jobPost) {
		super();
		this.jobPost = jobPost;
	}

	public ArrayList<Integer> getJobPost() {
		return jobPost;
	}

	public void setJobPost(ArrayList<Integer> jobPost) {
		this.jobPost = jobPost;
	}

	
	

}
