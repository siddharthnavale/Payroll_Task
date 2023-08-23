package com.recruitment_portal.Dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class EducationDetailsDto {

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date completionDate;
	
	private String degreeCertificateName;
	
	private String major;
	
	private double percentage;

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date startDate;
	
	private String universityName;

	public EducationDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EducationDetailsDto(Date completionDate, String degreeCertificateName, String major, double percentage,
			Date startDate, String universityName) {
		super();
		this.completionDate = completionDate;
		this.degreeCertificateName = degreeCertificateName;
		this.major = major;
		this.percentage = percentage;
		this.startDate = startDate;
		this.universityName = universityName;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getDegreeCertificateName() {
		return degreeCertificateName;
	}

	public void setDegreeCertificateName(String degreeCertificateName) {
		this.degreeCertificateName = degreeCertificateName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	
	

}
