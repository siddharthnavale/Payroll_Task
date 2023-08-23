package com.recruitment_portal.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.recruitment_portal.Dto.JobSeekerDetailsDto;
import com.recruitment_portal.Dto.RecruiterAddDto;
import com.recruitment_portal.Dto.RecruiterDetailsDto;
import com.recruitment_portal.Dto.UserDtoRequest;

public interface UserService {

	void addUser(@Valid UserDtoRequest userDtoRequest,MultipartFile file);

	Page<JobSeekerDetailsDto> getAllRegisteredCandidate(String search, String pageNo, String pageSize);

	Page<RecruiterDetailsDto> getAllRegisteredRecruiter(String search, String pageNo, String pageSize);

	void deleteRegisteredCandidate(int id) throws Exception;

	void deleteRecruiter(int id) throws Exception;

	List<JobSeekerDetailsDto> getAllRegisteredCandidate(String search);

	void exportAllRegisteredCandidate(HttpServletResponse response, List<JobSeekerDetailsDto> jobseeker) throws IOException;

	List<RecruiterDetailsDto> getAllRegisteredRecruiter(String search);

	void exportAllRegisteredRecruiter(HttpServletResponse response, List<RecruiterDetailsDto> recruiters) throws IOException;

	RecruiterAddDto addUser(RecruiterAddDto recruiterAddDto, int loggedInUserId, MultipartFile file);

	

}
