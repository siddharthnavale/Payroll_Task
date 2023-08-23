package com.recruitment_portal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Company_Image")
public class CompanyImage {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Company company;
	
	@Lob
	private byte[] comapanyImage;

	public CompanyImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyImage(Company company, byte[] comapanyImage) {
		super();
		this.company = company;
		this.comapanyImage = comapanyImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public byte[] getComapanyImage() {
		return comapanyImage;
	}

	public void setComapanyImage(byte[] comapanyImage) {
		this.comapanyImage = comapanyImage;
	}	
	
	
	
}
