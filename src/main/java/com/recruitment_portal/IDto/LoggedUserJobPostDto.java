package com.recruitment_portal.IDto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface LoggedUserJobPostDto {	
		
		@JsonFormat(pattern = "yyyy-MM-dd")
		public Date getAppliedDate();
		
		public String getJobRole();
		
		public String getJobDescription();

		public String getJobType();
		
		public String getL_ocation();
		
		public String getCompanyName();
		
		public String getCompanyWebsiteUrl();
	
}
