package com.recruitment_portal.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recruitment_portal.entities.JobPostSkillSet;

public interface JobPostSkillSetRepo extends JpaRepository<JobPostSkillSet, Integer>{

	
	@Transactional
	@Modifying
	@Query(value = "delete from job_post_skill_set where job_post_id=:jobPostId", nativeQuery = true)
	void deleteAllByJobPostId(@Param("jobPostId") int jobPostId);
}
