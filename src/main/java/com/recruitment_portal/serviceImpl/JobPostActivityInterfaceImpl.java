package com.recruitment_portal.serviceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recruitment_portal.Dto.JobAppliedDto;
import com.recruitment_portal.Dto.JobPostResponseDto;
import com.recruitment_portal.Dto.JobPostsDto;
import com.recruitment_portal.Dto.JobSeekerDetailsDto;
import com.recruitment_portal.Exception.ResourceNotFoundException;
import com.recruitment_portal.IDto.CandiadateAppliedJobPostIDto;
import com.recruitment_portal.IDto.IJobPostDto;
import com.recruitment_portal.IDto.LoggedUserJobPostDto;
import com.recruitment_portal.entities.JobPost;
import com.recruitment_portal.entities.JobPostActivity;
import com.recruitment_portal.entities.User;
import com.recruitment_portal.service.JobPostActivityInterface;
import com.recruitment_portal.util.ErrorMessageConstant;
import com.recruitment_portal.util.Pagination;
import com.recruitment_portal.repo.JobPostActivityRepo;
import com.recruitment_portal.repo.JobPostRepo;
import com.recruitment_portal.repo.UserRepository;

@Service
public class JobPostActivityInterfaceImpl implements JobPostActivityInterface {

	@Autowired
	private JobPostActivityRepo jobPostActivityRepo;
	
	@Autowired
	private JobPostRepo jobPostRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailInterface emailInterface;

	@Override
	public JobPostResponseDto saveJobPostActivity(JobPostsDto jobPostsDto, int loggedInUserId) {

		ArrayList<Integer> jobPosts = jobPostsDto.getJobPost();
		 HashSet <Integer> findJobPostAppliedByUserAndByJobPost = this.jobPostActivityRepo.findJobPostAppliedByUserAndByJobPost(jobPosts,
				loggedInUserId);
		 
		 List<JobPost> applyingForJobPost = jobPostRepo.findByIdIn(jobPosts);
		 if(applyingForJobPost.isEmpty()){
				throw new ResourceNotFoundException(ErrorMessageConstant.JOB_POST_NOT_FOUND);
			}
		 List<Integer> appliedJobPostId=new ArrayList<Integer>();
		 List<JobPostActivity> jobPostActivitys=new ArrayList<>();
		 JobPostResponseDto jobPostResponseDto = new JobPostResponseDto();
		 int alreadyApplied=0;
		 int applied=0;
		for(int i=0; i<jobPosts.size();i++) {
			if(findJobPostAppliedByUserAndByJobPost.contains(applyingForJobPost.get(i).getId())) {
				alreadyApplied++;
			}
			else {
				JobPostActivity jobPostActivity=new JobPostActivity();
				jobPostActivity.setAppliedBy(loggedInUserId);
				jobPostActivity.setJobPost(applyingForJobPost.get(i));
				jobPostActivitys.add(jobPostActivity);
				applied++;
				appliedJobPostId.add(applyingForJobPost.get(i).getId());
			}
		}
		this.jobPostActivityRepo.saveAll(jobPostActivitys);
		jobPostResponseDto.setAlreadyAppliedFor("You already applied for "+alreadyApplied+ " jobs");
		jobPostResponseDto.setAppliedFor("You applied for "+applied+ " jobs");
		Optional<User> user = this.userRepository.findById(loggedInUserId);
		this.emailInterface.sendSimpleMailToJobSeeker(applied,user.get().getEmail());
		this.emailInterface.sendSimpleMailToRecruiter(appliedJobPostId,loggedInUserId);
		return jobPostResponseDto;
	}

	@Override
	public Page<LoggedUserJobPostDto> getAllJobPostAppliedByLoggedUser(String pageNo, String pageSize,
			int loggedInUserId) {
		Pageable pageable =new Pagination().getPagination(pageNo,pageSize);
		Page<LoggedUserJobPostDto> page=this.jobPostActivityRepo.getAllJobPostAppliedByLoggedUser(pageable,loggedInUserId,LoggedUserJobPostDto.class);
		return page;
	}

	@Override
	public Page<JobAppliedDto> getAllJobAppliedForSpecificJobPost(int jobPostId, int loggedInUserId, String pageNo,
			String pageSize) {
		Pageable pageable =new Pagination().getPagination(pageNo,pageSize);
		Page<JobAppliedDto> page=this.jobPostActivityRepo.getAllJobAppliedForSpecificJobPost(pageable,jobPostId,loggedInUserId,JobAppliedDto.class);
		return page;
	}

	@Override
	public List<CandiadateAppliedJobPostIDto> getAllCandiadateAppliedJobPost() {
		List<CandiadateAppliedJobPostIDto> allCandiadateAppliedJobPost =jobPostActivityRepo.getAllCandiadateAppliedJobPost(CandiadateAppliedJobPostIDto.class);
		return allCandiadateAppliedJobPost;
	}

	@Override
	public void exportAllCandiadateAppliedJobPost(HttpServletResponse response,
			List<CandiadateAppliedJobPostIDto> candidateAppliedToJob) throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("Email").append(",").append("Gender")
				.append(",").append("PhoneNumber");
		builder.append('\n');
	
		for (CandiadateAppliedJobPostIDto row : candidateAppliedToJob) {
			builder.append(row.getAppliedBy() != null ? row.getAppliedBy() : "").append(",")
					.append(row.getEmail() != null ? row.getEmail() : "").append(",")
					.append(row.getAppliedDate() != null ? row.getAppliedDate()  : "").append(",")
					.append(row.getJobPostId() != null ? row.getJobPostId()  : "").append(",")
					.append(row.getJobRole() != null ? row.getJobRole()  : "").append(",")
					.append(row.getCompanyName() != null ? row.getCompanyName()  : "");

			builder.append('\n');
		}

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=AllRegisteredCandidate.csv");

		PrintWriter writer = response.getWriter();
		writer.write(builder.toString());
		writer.flush();
		writer.close();
		
	}

	

	
}
