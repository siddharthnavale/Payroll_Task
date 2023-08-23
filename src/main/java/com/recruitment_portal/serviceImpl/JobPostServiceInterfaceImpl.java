package com.recruitment_portal.serviceImpl;

import java.util.ArrayList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitment_portal.Dto.JobPostRequestDto;
import com.recruitment_portal.Exception.ResourceNotFoundException;
import com.recruitment_portal.entities.Company;
import com.recruitment_portal.entities.JobPost;
import com.recruitment_portal.entities.JobPostSkillSet;
import com.recruitment_portal.entities.JobType;
import com.recruitment_portal.repo.CompanyRepo;
import com.recruitment_portal.repo.JobPostRepo;
import com.recruitment_portal.repo.JobPostSkillSetRepo;
import com.recruitment_portal.service.JobPostServiceInterface;
import com.recruitment_portal.util.ErrorMessageConstant;

@Service
public class JobPostServiceInterfaceImpl implements JobPostServiceInterface{

	@Autowired
	private CompanyRepo companyRepo;
	
	@Autowired
	private JobPostRepo jobPostRepo;
	
	@Autowired
	private JobPostSkillSetRepo jobPostSkillSetRepo;
	
	
	@Override
	public void postAJob(JobPostRequestDto jobPostRequestDto, int loggedInUserId) {
		
		JobPost jobPost=new JobPost();
		jobPost.setActive(true);
		jobPost.setJobDescription(jobPostRequestDto.getJobDescription());
		jobPost.setJobType(Enum.valueOf(JobType.class, jobPostRequestDto.getJobType()));
		jobPost.setLocation(jobPostRequestDto.getLocation());
		jobPost.setPostedBy(loggedInUserId);
		
		Company company = companyRepo.findById(jobPostRequestDto.getCompanyId()).orElseThrow(() ->
		new ResourceNotFoundException(ErrorMessageConstant.COMPANY_NOT_FOUND));
		
		jobPost.setCompany(company);
		jobPost.setJobRole(jobPostRequestDto.getJobRole());
		jobPostRepo.save(jobPost);
		
		ArrayList <JobPostSkillSet> skillSetsForSpecificJobPost=new ArrayList<>();
		
		for(int i=0;i<jobPostRequestDto.getSkillSetRequiredForJob().size();i++) {
			JobPostSkillSet jobPostSkillSet =new JobPostSkillSet();
			jobPostSkillSet.setJobPost(jobPost);
			jobPostSkillSet.setSkillSet(jobPostRequestDto.getSkillSetRequiredForJob().get(i));
			skillSetsForSpecificJobPost.add(jobPostSkillSet);
		}
		jobPostSkillSetRepo.saveAll(skillSetsForSpecificJobPost);
	}


	@Override
	public void deleteJobPost(int jobPostId) throws Exception {
		
		this.jobPostRepo.findById(jobPostId).orElseThrow(() -> new Exception(ErrorMessageConstant.JOB_POST_NOT_FOUND));
		this.jobPostSkillSetRepo.deleteAllByJobPostId(jobPostId);
		this.jobPostRepo.deleteByJobPostId(jobPostId);
	}

}
