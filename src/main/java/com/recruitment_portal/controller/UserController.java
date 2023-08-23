package com.recruitment_portal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.recruitment_portal.Dto.ErrorResponseDto;
import com.recruitment_portal.Dto.JobSeekerDetailsDto;
import com.recruitment_portal.Dto.ListResponseDto;
import com.recruitment_portal.Dto.PaginationResponse;
import com.recruitment_portal.Dto.RecruiterAddDto;
import com.recruitment_portal.Dto.RecruiterDetailsDto;
import com.recruitment_portal.Dto.SuccessResponseDto;
import com.recruitment_portal.Dto.UserDtoRequest;
import com.recruitment_portal.IDto.IJobPostDto;
import com.recruitment_portal.entities.User;
import com.recruitment_portal.repo.UserRepository;
import com.recruitment_portal.service.UserService;
import com.recruitment_portal.util.ApiUrls;
import com.recruitment_portal.util.Constant;
import com.recruitment_portal.util.ErrorKeyConstant;
import com.recruitment_portal.util.ErrorMessageConstant;
import com.recruitment_portal.util.SuccessKeyConstant;
import com.recruitment_portal.util.SuccessMessageConstant;

@RestController
@RequestMapping(ApiUrls.USER)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/sign-up")
	public ResponseEntity<?> addUser(@Validated UserDtoRequest userDtoRequest,
			@RequestParam(name = "file", required = false) MultipartFile file) {
		User user = this.userRepository.findByEmailIgnoreCase(userDtoRequest.getEmail().trim());
		if (user == null) {

			this.userService.addUser(userDtoRequest, file);

			return new ResponseEntity<>(
					new SuccessResponseDto(SuccessMessageConstant.USER_ADDED, SuccessKeyConstant.USER_M031201),
					HttpStatus.OK);
		} else {

			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.USER_ALREADY_PRESENT, ErrorKeyConstant.USER_E031201),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('GetAllRegisteredCandidate')")
	@GetMapping()
	public ResponseEntity<?> getAllRegisteredCandidate(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1", value = Constant.PAGENUMBER) String pageNo,
			@RequestParam(defaultValue = "10", value = Constant.PAGESIZE) String pageSize,
			@RequestParam(defaultValue = "false") Boolean export, HttpServletResponse response) throws IOException {

		if (export == false) {
			Page<JobSeekerDetailsDto> jobseeker = userService.getAllRegisteredCandidate(search, pageNo, pageSize);

			PaginationResponse paginationResponse = new PaginationResponse();

			paginationResponse.setPageSize(jobseeker.getSize());
			paginationResponse.setTotal(jobseeker.getTotalElements());
			paginationResponse.setPageNumber(jobseeker.getNumber() + 1);

			return new ResponseEntity<>(new ListResponseDto(jobseeker.getContent(), paginationResponse), HttpStatus.OK);
		} else {
			List<JobSeekerDetailsDto> jobseeker = this.userService.getAllRegisteredCandidate(search);

			userService.exportAllRegisteredCandidate(response, jobseeker);

			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('GetAllRegisteredRecruiter')")
	@GetMapping("/recruiter")
	public ResponseEntity<?> getAllRegisteredRecruiter(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1", value = Constant.PAGENUMBER) String pageNo,
			@RequestParam(defaultValue = "10", value = Constant.PAGESIZE) String pageSize,
			@RequestParam(defaultValue = "false") Boolean export, HttpServletResponse response) throws IOException {

		if (export == false) {
			Page<RecruiterDetailsDto> recruiter = userService.getAllRegisteredRecruiter(search, pageNo, pageSize);

			PaginationResponse paginationResponse = new PaginationResponse();

			paginationResponse.setPageSize(recruiter.getSize());
			paginationResponse.setTotal(recruiter.getTotalElements());
			paginationResponse.setPageNumber(recruiter.getNumber() + 1);

			return new ResponseEntity<>(new ListResponseDto(recruiter.getContent(), paginationResponse), HttpStatus.OK);
		} else {
			List<RecruiterDetailsDto> recruiters = this.userService.getAllRegisteredRecruiter(search);

			userService.exportAllRegisteredRecruiter(response, recruiters);

			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('DeleteRegisteredCandidate')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRegisteredCandidate(@PathVariable int id) {
		try {
			this.userService.deleteRegisteredCandidate(id);
			return new ResponseEntity<>(
					new SuccessResponseDto(SuccessMessageConstant.USER_DELETED, SuccessKeyConstant.USER_M031203),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.USER_NOT_FOUND, ErrorKeyConstant.USER_E031203),
					HttpStatus.BAD_REQUEST);

		}
	}

	@PreAuthorize("hasRole('DeleteRecruiter')")
	@DeleteMapping("/recruiter/{id}")
	public ResponseEntity<?> deleteRecruiter(@PathVariable int id) {
		try {
			this.userService.deleteRecruiter(id);
			return new ResponseEntity<>(
					new SuccessResponseDto(SuccessMessageConstant.USER_DELETED, SuccessKeyConstant.USER_M031203),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.USER_NOT_FOUND, ErrorKeyConstant.USER_E031203),
					HttpStatus.BAD_REQUEST);

		}
	}

	
	@PreAuthorize("hasRole('UserOnlyAddedByAdmin')")
	@PostMapping()
	public ResponseEntity<?> addUserOnlyAdminOrMentor(@Validated RecruiterAddDto recruiterAddDto,
			@RequestParam(name = "file", required = false) MultipartFile file, HttpServletRequest request,
			@RequestAttribute("X-user-id") int loggedInUserId) throws DataIntegrityViolationException, Exception {
		try {

			User databaseUser = this.userRepository.findByEmailIgnoreCase(recruiterAddDto.getEmail());

			if (databaseUser == null) {
				this.userService.addUser(recruiterAddDto, loggedInUserId,file);

				return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageConstant.USER_ADDED,
						SuccessKeyConstant.USER_M031201, recruiterAddDto), HttpStatus.CREATED);

			} else {
				return new ResponseEntity<>(new ErrorResponseDto(ErrorMessageConstant.EMAIL_ID_ALREADY_EXISTS,
						ErrorKeyConstant.USER_E031206), HttpStatus.BAD_REQUEST);
			}

		}

		catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.USER_ALREADY_PRESENT, ErrorKeyConstant.USER_E031201),
					HttpStatus.BAD_REQUEST);
		}
	}
}
