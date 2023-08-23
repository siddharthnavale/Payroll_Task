package com.recruitment_portal.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recruitment_portal.Dto.UserTypeDto;
import com.recruitment_portal.IDto.IJobPostDto;
import com.recruitment_portal.entities.UserType;
import com.recruitment_portal.repo.UserTypeRepository;
import com.recruitment_portal.service.UserTypeServiceInterface;
import com.recruitment_portal.util.Pagination;

@Service
public class userTypeServiceInterfaceImpl implements UserTypeServiceInterface {

	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@Override
	public void addRole(UserTypeDto userTypeDto) {
		
		UserType userType = new UserType();
		userType.setUserTypeName(userTypeDto.getUserTypeName());
		userTypeRepository.save(userType);
		
	}

	@Override
	public ArrayList<String> getPermissionByUserId(int id) {
		
		return userTypeRepository.getPermissionNameBasedOnUser(id);
	}

	@Override
	public Page<IJobPostDto> getAllJobPost(String search, String pageNo, String pageSize) {
		
		Pageable pageable =new Pagination().getPagination(pageNo,pageSize);
		Page<IJobPostDto> page=userTypeRepository.getAllJobPost(search,pageable,IJobPostDto.class);
		return page;
	}

}
