package com.recruitment_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_portal.Dto.ErrorResponseDto;
import com.recruitment_portal.Dto.JobAppliedDto;
import com.recruitment_portal.Dto.JobPostRequestDto;
import com.recruitment_portal.Dto.ListResponseDto;
import com.recruitment_portal.Dto.PaginationResponse;
import com.recruitment_portal.Dto.SuccessResponseDto;
import com.recruitment_portal.IDto.IJobPostDto;
import com.recruitment_portal.service.JobPostActivityInterface;
import com.recruitment_portal.service.JobPostServiceInterface;
import com.recruitment_portal.service.UserTypeServiceInterface;
import com.recruitment_portal.util.ApiUrls;
import com.recruitment_portal.util.Constant;
import com.recruitment_portal.util.ErrorKeyConstant;
import com.recruitment_portal.util.ErrorMessageConstant;
import com.recruitment_portal.util.SuccessKeyConstant;
import com.recruitment_portal.util.SuccessMessageConstant;

@RestController
@RequestMapping(ApiUrls.JOBPOST)
public class JobPostController {

	@Autowired
	private UserTypeServiceInterface userTypeServiceInterface;
	
	@Autowired
	private JobPostServiceInterface jobPostServiceInterface;
	
	@Autowired
	private JobPostActivityInterface jobPostActivityInterface;

	@PreAuthorize("hasRole('GetAllJobPost')")
	@GetMapping()
	public ResponseEntity<?> getAllJobPost(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1", value = Constant.PAGENUMBER) String pageNo,
			@RequestParam(defaultValue = "10", value = Constant.PAGESIZE) String pageSize) {

		Page<IJobPostDto> jobPosts = userTypeServiceInterface.getAllJobPost(search, pageNo, pageSize);

		PaginationResponse paginationResponse = new PaginationResponse();

		paginationResponse.setPageSize(jobPosts.getSize());
		paginationResponse.setTotal(jobPosts.getTotalElements());
		paginationResponse.setPageNumber(jobPosts.getNumber() + 1);

		return new ResponseEntity<>(new ListResponseDto(jobPosts.getContent(), paginationResponse), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('PostAJob')")
	@PostMapping()
	public ResponseEntity<?> postAJob(@RequestBody JobPostRequestDto jobPostRequestDto,
			@RequestAttribute("X-user-id") int loggedInUserId) {
		try {
			this.jobPostServiceInterface.postAJob(jobPostRequestDto, loggedInUserId	);

			return new ResponseEntity<>(
					new SuccessResponseDto(SuccessMessageConstant.JOB_POSTED, SuccessKeyConstant.JOBPOST_M031401),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.COMPANY_NOT_FOUND, ErrorKeyConstant.USER_E031201),
					HttpStatus.BAD_REQUEST);

		}
	}

	
	@PreAuthorize("hasRole('GetAllJobAppliedForSpecificJobPost')")
	@GetMapping("/{jobPostId}")
	public ResponseEntity<?> getAllJobAppliedForSpecificJobPost(@PathVariable(name = "jobPostId") int jobPostId,
			@RequestAttribute("X-user-id") int loggedInUserId,
			@RequestParam(defaultValue = "1", value = Constant.PAGENUMBER) String pageNo,
			@RequestParam(defaultValue = "10", value = Constant.PAGESIZE) String pageSize) {

		Page<JobAppliedDto> JobAppliedForSpecificJobPost = jobPostActivityInterface.getAllJobAppliedForSpecificJobPost(jobPostId,loggedInUserId, pageNo, pageSize);

		PaginationResponse paginationResponse = new PaginationResponse();

		paginationResponse.setPageSize(JobAppliedForSpecificJobPost.getSize());
		paginationResponse.setTotal(JobAppliedForSpecificJobPost.getTotalElements());
		paginationResponse.setPageNumber(JobAppliedForSpecificJobPost.getNumber() + 1);

		return new ResponseEntity<>(new ListResponseDto(JobAppliedForSpecificJobPost.getContent(), paginationResponse), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('DeleteJobPost')")
	@DeleteMapping("/{jobPostId}")
	public ResponseEntity<?> deleteJobPost(@PathVariable int jobPostId) {
		try {
			this.jobPostServiceInterface.deleteJobPost(jobPostId);
			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageConstant.JOB_POST_DELETED,
					SuccessKeyConstant.JOBPOST_M031402), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto(ErrorMessageConstant.JOB_POST_NOT_FOUND,
					ErrorKeyConstant.JOB_POST_E031301), HttpStatus.BAD_REQUEST);

		}
	}

}
