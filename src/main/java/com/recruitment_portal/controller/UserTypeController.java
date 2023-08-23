package com.recruitment_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_portal.Dto.ErrorResponseDto;
import com.recruitment_portal.Dto.SuccessResponseDto;
import com.recruitment_portal.Dto.UserTypeDto;
import com.recruitment_portal.entities.UserType;
import com.recruitment_portal.repo.UserTypeRepository;
import com.recruitment_portal.service.UserTypeServiceInterface;
import com.recruitment_portal.util.ApiUrls;
import com.recruitment_portal.util.ErrorKeyConstant;
import com.recruitment_portal.util.ErrorMessageConstant;
import com.recruitment_portal.util.SuccessKeyConstant;
import com.recruitment_portal.util.SuccessMessageConstant;

@RestController
@RequestMapping(ApiUrls.USERTYPE)
public class UserTypeController {

	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@Autowired
	private UserTypeServiceInterface userTypeServiceInterface;
	
	@PostMapping
	public ResponseEntity<?> addRoles(@RequestBody UserTypeDto userTypeDto) {

			UserType userType =this.userTypeRepository.findByUserTypeNameIgnoreCase(userTypeDto.getUserTypeName().trim());
			if (userType == null) {
				
				this.userTypeServiceInterface.addRole(userTypeDto);

				return new ResponseEntity<>(
						new SuccessResponseDto(SuccessMessageConstant.USERTYPE_ADDED, SuccessKeyConstant.USERTYPE_M031101),
						HttpStatus.OK);
			} else {

				return new ResponseEntity<>(
						new ErrorResponseDto(ErrorMessageConstant.USERTYPE_ALREADY_PRESENT, ErrorKeyConstant.USERTYPE_E031101),
						HttpStatus.BAD_REQUEST);
			}

	}
}
