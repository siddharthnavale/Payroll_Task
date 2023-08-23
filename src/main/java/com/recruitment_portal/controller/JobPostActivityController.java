package com.recruitment_portal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_portal.Dto.ErrorResponseDto;
import com.recruitment_portal.Dto.JobPostResponseDto;
import com.recruitment_portal.Dto.JobPostsDto;
import com.recruitment_portal.Dto.ListResponseDto;
import com.recruitment_portal.Dto.PaginationResponse;
import com.recruitment_portal.Dto.RecruiterDetailsDto;
import com.recruitment_portal.Dto.SuccessResponseDto;
import com.recruitment_portal.IDto.CandiadateAppliedJobPostIDto;
import com.recruitment_portal.IDto.IJobPostDto;
import com.recruitment_portal.IDto.LoggedUserJobPostDto;
import com.recruitment_portal.service.JobPostActivityInterface;
import com.recruitment_portal.util.ApiUrls;
import com.recruitment_portal.util.Constant;
import com.recruitment_portal.util.ErrorKeyConstant;
import com.recruitment_portal.util.ErrorMessageConstant;
import com.recruitment_portal.util.SuccessKeyConstant;

@RestController
@RequestMapping(ApiUrls.JOBPOSTACTIVITY)
public class JobPostActivityController {

	@Autowired
	private JobPostActivityInterface JobPostActivityInterface;

	@PreAuthorize("hasRole('ApplyForMultipleJob')")
	@PostMapping()
	public ResponseEntity<?> applyToJobPost(@RequestBody JobPostsDto JobPostsDto,
			@RequestAttribute("X-user-id") int loggedInUserId) {
		try {
			JobPostResponseDto jobPostResponseDto = this.JobPostActivityInterface.saveJobPostActivity(JobPostsDto,
					loggedInUserId);
			return new ResponseEntity<>(new SuccessResponseDto(jobPostResponseDto, SuccessKeyConstant.JOBPOSTACTIVITY_M031301),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorResponseDto(ErrorMessageConstant.JOB_POST_NOT_FOUND,
					ErrorKeyConstant.JOB_POST_E031301), HttpStatus.BAD_REQUEST);

		}
	}
	
	    @PreAuthorize("hasRole('GetAllAppliedByLogedUser')")
		@GetMapping()
		public ResponseEntity<?> getAllJobPostAppliedByLoggedUser(
				@RequestParam(defaultValue = "1", value = Constant.PAGENUMBER) String pageNo,
				@RequestParam(defaultValue = "10", value = Constant.PAGESIZE) String pageSize
				,@RequestAttribute("X-user-id") int loggedInUserId) {
			
			Page<LoggedUserJobPostDto> jobPostsAppliedByLoggedUser = JobPostActivityInterface.getAllJobPostAppliedByLoggedUser(pageNo, pageSize,loggedInUserId);

			PaginationResponse paginationResponse = new PaginationResponse();

			paginationResponse.setPageSize(jobPostsAppliedByLoggedUser.getSize());
			paginationResponse.setTotal(jobPostsAppliedByLoggedUser.getTotalElements());
			paginationResponse.setPageNumber(jobPostsAppliedByLoggedUser.getNumber() + 1);

			return new ResponseEntity<>(new ListResponseDto(jobPostsAppliedByLoggedUser.getContent(), paginationResponse), HttpStatus.OK);
		}

		@PreAuthorize("hasRole('GetAllCandiadateAppliedJobPost')")
		@GetMapping("/export")
		public ResponseEntity<?> getAllCandiadateAppliedJobPost(HttpServletResponse response) throws IOException {

			
				List<CandiadateAppliedJobPostIDto> candidateAppliedToJob = this.JobPostActivityInterface.getAllCandiadateAppliedJobPost();

				JobPostActivityInterface.exportAllCandiadateAppliedJobPost(response, candidateAppliedToJob);

				return new ResponseEntity<>(HttpStatus.OK);
			
		}
}
