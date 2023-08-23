package com.recruitment_portal.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.recruitment_portal.Dto.JobAppliedDto;
import com.recruitment_portal.Dto.JobPostResponseDto;
import com.recruitment_portal.Dto.JobPostsDto;
import com.recruitment_portal.IDto.CandiadateAppliedJobPostIDto;
import com.recruitment_portal.IDto.LoggedUserJobPostDto;

public interface JobPostActivityInterface {

	JobPostResponseDto saveJobPostActivity(JobPostsDto jobPostsDto, int loggedInUserId);

	Page<LoggedUserJobPostDto> getAllJobPostAppliedByLoggedUser(String pageNo, String pageSize,
			int loggedInUserId);

	Page<JobAppliedDto> getAllJobAppliedForSpecificJobPost(int jobPostId, int loggedInUserId, String pageNo,
			String pageSize);

	List<CandiadateAppliedJobPostIDto> getAllCandiadateAppliedJobPost();

	void exportAllCandiadateAppliedJobPost(HttpServletResponse response,
			List<CandiadateAppliedJobPostIDto> candidateAppliedToJob) throws IOException;

}
