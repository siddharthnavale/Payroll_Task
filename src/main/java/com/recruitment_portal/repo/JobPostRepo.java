package com.recruitment_portal.repo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recruitment_portal.IDto.JobPostActivityDto;
import com.recruitment_portal.entities.JobPost;

public interface JobPostRepo extends JpaRepository<JobPost, Integer> {

	List<JobPost> findByIdIn(ArrayList<Integer> jobPosts);
	
	@Query(value = "select u.id as Recruiterid, u.email as RecruiterEmail,string_agg(jp.job_role,', ') as AppliedJobRoles\r\n"
			+ ",count(*) as AppliedCountPerRecruiter from job_post jp join users u on jp.posted_by=u.id\r\n"
			+ "where jp.id in :appliedJobPostId group by u.id", nativeQuery = true)
	List<JobPostActivityDto>getDetailsOfAppliedJobPost(@Param("appliedJobPostId")List<Integer> appliedJobPostId);

	
	@Transactional
	@Modifying
	@Query(value = "update job_post set is_active=false where id=:jobPostId", nativeQuery = true)
	void deleteByJobPostId(@Param("jobPostId") int jobPostId);
}
