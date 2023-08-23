package com.recruitment_portal.Dto;

public class SeekerSkillSetDto {

	private String skillSet;
	
	private String skillSetLevel;

	public SeekerSkillSetDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeekerSkillSetDto(String skillSet, String skillSetLevel) {
		super();
		this.skillSet = skillSet;
		this.skillSetLevel = skillSetLevel;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public String getSkillSetLevel() {
		return skillSetLevel;
	}

	public void setSkillSetLevel(String skillSetLevel) {
		this.skillSetLevel = skillSetLevel;
	}
	
	
}
