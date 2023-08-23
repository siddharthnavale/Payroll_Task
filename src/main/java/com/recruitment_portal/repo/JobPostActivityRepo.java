package com.recruitment_portal.repo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recruitment_portal.Dto.JobAppliedDto;
import com.recruitment_portal.Dto.RecruiterDetailsDto;
import com.recruitment_portal.IDto.CandiadateAppliedJobPostIDto;
import com.recruitment_portal.IDto.LoggedUserJobPostDto;
import com.recruitment_portal.entities.JobPostActivity;

public interface JobPostActivityRepo extends JpaRepository<JobPostActivity, Integer>{

	@Query(value = "select jpa.job_post_id FROM job_post_activity jpa where jpa.job_post_id in :jobPosts and \r\n"
			+ "jpa.applied_By =:loggedInUserId", nativeQuery = true)
	HashSet<Integer> findJobPostAppliedByUserAndByJobPost(@Param("jobPosts")ArrayList<Integer> jobPosts, @Param("loggedInUserId") int loggedInUserId);

	
	
	@Query(value = "select jpa.applied_date as AppliedDate,jp.job_role as JobRole,\r\n"
			+ "jp.job_description as JobDescription, jp.job_type as JobType,\r\n"
			+ "jp.location as L_ocation,com.company_name as CompanyName\r\n"
			+ ",com.company_website_url as CompanyWebsiteUrl\r\n"
			+ "from job_post_activity jpa join job_post jp on jpa.job_post_id=jp.id\r\n"
			+ "join companies com on com.id=jp.company_id where jpa.applied_by =:loggedInUserId\r\n"
			+ "order by AppliedDate desc", nativeQuery = true)
	Page<LoggedUserJobPostDto> getAllJobPostAppliedByLoggedUser(Pageable pageable, @Param("loggedInUserId") int loggedInUserId,
			Class<LoggedUserJobPostDto> class1);



	@Query(value = "SELECT jpa.applied_by as AppledBy,u.email as Email,u.gender as Gender,\r\n"
			+ "u.phone_number as PhoneNumber FROM job_post_activity jpa join users u\r\n"
			+ "on jpa.applied_by=u.id where jpa.job_post_id =:jobPostId and\r\n"
			+ "jpa.job_post_id in (select jp.id from job_post jp where jp.posted_by =:loggedInUserId)\r\n"
			+ "ORDER BY jpa.id desc", nativeQuery = true)
	Page<JobAppliedDto> getAllJobAppliedForSpecificJobPost(Pageable pageable, @Param("jobPostId") int jobPostId,
			@Param("loggedInUserId") int loggedInUserId,Class<JobAppliedDto> class1);

	
	@Query(value = "SELECT jpa.applied_by AS AppliedBy,u.email AS Email ,jpa.applied_date AS AppliedDate\r\n"
			+ ",jpa.job_post_id AS JobPostId, jp.job_role AS JobRole, com.company_name AS CompanyName\r\n"
			+ "FROM job_post_activity jpa\r\n"
			+ "join job_post jp on jpa.job_post_id=jp.id\r\n"
			+ "join users u on jpa.applied_by=u.id\r\n"
			+ "join companies com on jp.company_id=com.id\r\n"
			+ "ORDER BY jpa.id desc", nativeQuery = true)
	List<CandiadateAppliedJobPostIDto> getAllCandiadateAppliedJobPost(Class<CandiadateAppliedJobPostIDto> class1);
	
}
