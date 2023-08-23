package com.recruitment_portal.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Business_Stream")
public class BusinessStream {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	private String businessStreamName;
	
	@OneToMany(mappedBy = "businessStream")
	private List<Company> company;

	public BusinessStream() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessStream(String businessStreamName, List<Company> company) {
		super();
		this.businessStreamName = businessStreamName;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusinessStreamName() {
		return businessStreamName;
	}

	public void setBusinessStreamName(String businessStreamName) {
		this.businessStreamName = businessStreamName;
	}

	public List<Company> getCompany() {
		return company;
	}

	public void setCompany(List<Company> company) {
		this.company = company;
	}


	
}
