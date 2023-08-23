package com.recruitment_portal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_portal.Dto.ErrorResponseDto;
import com.recruitment_portal.Dto.JwtRequest;
import com.recruitment_portal.Dto.JwtResponseDto;
import com.recruitment_portal.Dto.SuccessResponseDto;
import com.recruitment_portal.configuration.JwtTokenUtil;
import com.recruitment_portal.entities.User;
import com.recruitment_portal.repo.UserRepository;
import com.recruitment_portal.repo.UserTypeRepository;
import com.recruitment_portal.serviceImpl.CustomerUserDetailsService;
import com.recruitment_portal.util.ErrorKeyConstant;
import com.recruitment_portal.util.ErrorMessageConstant;
import com.recruitment_portal.util.SuccessKeyConstant;
import com.recruitment_portal.util.SuccessMessageConstant;

@RestController
@RequestMapping("/jwt")
public class JWTController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		}
		 catch(Exception e){
			 return new ResponseEntity<>(
						new ErrorResponseDto(ErrorMessageConstant.INVALID_INFORMATION, ErrorKeyConstant.USER_E031202),
						HttpStatus.BAD_REQUEST);
			 
		 }
		UserDetails userDetails = this.customerUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
		String token=this.jwtUtil.generateToken(userDetails);
		User userEntity = userRepository.findByEmailIgnoreCase(jwtRequest.getEmail());
		ArrayList<String> permissionNames = userTypeRepository.getPermissionNameBasedOnUser(userEntity.getId());
		int roleId = userTypeRepository.getRoleBasedOnUser(userEntity.getId());
		String role = (userTypeRepository.findById(roleId)).getUserTypeName();
		return new ResponseEntity<>(
				new SuccessResponseDto(SuccessMessageConstant.LOGIN_SUCCESSFULL,
						SuccessKeyConstant.USER_M031202, new JwtResponseDto(token,permissionNames,
								userEntity.getEmail(), role)),HttpStatus.OK);
		
		
	}
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

