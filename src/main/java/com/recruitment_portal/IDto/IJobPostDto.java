package com.recruitment_portal.IDto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface IJobPostDto {
	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getCreatedDate();
	

	public String getJobDescription();

	public String getJobType();
	
	public String getLocation();
	
	public String getCompanyName();
	
	public String getCompanyWebsiteUrl();
}
