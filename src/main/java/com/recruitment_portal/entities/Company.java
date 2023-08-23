package com.recruitment_portal.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Companies")
public class Company {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	private String companyName;
	
	private String descriptionOfCompany;
	
	@ManyToOne
	private BusinessStream businessStream;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date enstablishmentDate;
	
	private String companyWebsiteUrl;
	
	@OneToMany(mappedBy = "company")
	private List<CompanyImage> comapanyImages;

    @OneToMany(mappedBy = "company")
	private List<Location> Location;
    
    @OneToMany(mappedBy = "company")
    private List<JobPost> jobPost;

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(String companyName, String descriptionOfCompany, BusinessStream businessStream,
			Date enstablishmentDate, String companyWebsiteUrl, List<CompanyImage> comapanyImages,
			List<com.recruitment_portal.entities.Location> location, List<JobPost> jobPost) {
		super();
		this.companyName = companyName;
		this.descriptionOfCompany = descriptionOfCompany;
		this.businessStream = businessStream;
		this.enstablishmentDate = enstablishmentDate;
		this.companyWebsiteUrl = companyWebsiteUrl;
		this.comapanyImages = comapanyImages;
		Location = location;
		this.jobPost = jobPost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescriptionOfCompany() {
		return descriptionOfCompany;
	}

	public void setDescriptionOfCompany(String descriptionOfCompany) {
		this.descriptionOfCompany = descriptionOfCompany;
	}

	public BusinessStream getBusinessStream() {
		return businessStream;
	}

	public void setBusinessStream(BusinessStream businessStream) {
		this.businessStream = businessStream;
	}

	public Date getEnstablishmentDate() {
		return enstablishmentDate;
	}

	public void setEnstablishmentDate(Date enstablishmentDate) {
		this.enstablishmentDate = enstablishmentDate;
	}

	public String getCompanyWebsiteUrl() {
		return companyWebsiteUrl;
	}

	public void setCompanyWebsiteUrl(String companyWebsiteUrl) {
		this.companyWebsiteUrl = companyWebsiteUrl;
	}

	public List<CompanyImage> getComapanyImages() {
		return comapanyImages;
	}

	public void setComapanyImages(List<CompanyImage> comapanyImages) {
		this.comapanyImages = comapanyImages;
	}

	public List<Location> getLocation() {
		return Location;
	}

	public void setLocation(List<Location> location) {
		Location = location;
	}

	public List<JobPost> getJobPost() {
		return jobPost;
	}

	public void setJobPost(List<JobPost> jobPost) {
		this.jobPost = jobPost;
	}
    
	
}
