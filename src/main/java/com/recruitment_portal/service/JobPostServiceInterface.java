package com.recruitment_portal.service;

import com.recruitment_portal.Dto.JobPostRequestDto;

public interface JobPostServiceInterface {

	void postAJob(JobPostRequestDto jobPostRequestDto, int loggedInUserId);

	void deleteJobPost(int jobPostId) throws Exception;

}
