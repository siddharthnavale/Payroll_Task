package com.recruitment_portal.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.recruitment_portal.Dto.UserTypeDto;
import com.recruitment_portal.IDto.IJobPostDto;

public interface UserTypeServiceInterface {

	void addRole(UserTypeDto userTypeDto);

	ArrayList<String> getPermissionByUserId(int id);

	Page<IJobPostDto> getAllJobPost(String search, String pageNo, String pageSize);

	

}
