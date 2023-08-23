package com.recruitment_portal.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recruitment_portal.Dto.JobSeekerDetailsDto;
import com.recruitment_portal.Dto.RecruiterDetailsDto;
import com.recruitment_portal.IDto.IJobPostDto;
import com.recruitment_portal.entities.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer>  {

	UserType findByUserTypeNameIgnoreCase(String userTypeName);
	
	UserType findById(int id);
	
	@Query(value = "select p.action_name from permissions p where p.id in\r\n"
			+ "(select utp.permission_id from user_type_permission utp where utp.user_type_id in \r\n"
			+ "(SELECT u.user_type_id FROM users u where u.id=:userId))", nativeQuery = true)
	ArrayList<String> getPermissionNameBasedOnUser(@Param("userId") int userId);
	
	@Query(value = "select u.user_type_id from users u where u.id=:userId", nativeQuery = true)
	int getRoleBasedOnUser(@Param("userId") int userId);
	
	
	@Query(value = "SELECT jp.created_date as CreatedDate,jp.job_description AS JobDescription,\r\n"
			+ "jp.job_type AS JobType,jp.location AS Location,com.company_name AS CompanyName,\r\n"
			+ "com.company_website_url AS CompanyWebsiteUrl\r\n"
			+ "FROM job_post jp join companies com on jp.company_id=com.id\r\n"
			+ "Where jp.job_description ilike %:search% or jp.location ilike %:search%\r\n"
			+ "ORDER BY jp.created_date Desc", nativeQuery = true)
	Page<IJobPostDto> getAllJobPost(String search, Pageable pageable, Class<IJobPostDto> class1);

	
	
	@Query(value = "SELECT u.email as Email,u.gender as Gender,u.phone_number as PhoneNumber \r\n"
			+ "FROM users u where u.user_type_id=2 and u.is_active=true"
			+ "and (u.email ilike %:search% or u.phone_number ilike %:search%)", nativeQuery = true)
	Page<JobSeekerDetailsDto> getAllRegisteredCandidate(String search, Pageable pageable,
			Class<JobSeekerDetailsDto> class1);

	@Query(value = "SELECT u.email as Email,u.gender as Gender,u.phone_number as PhoneNumber \r\n"
			+ "FROM users u where u.user_type_id=3 and u.is_active=true "
			+ "and (u.email ilike %:search% or u.phone_number ilike %:search%)", nativeQuery = true)
	Page<RecruiterDetailsDto> getAllRegisteredRecruiter(String search, Pageable pageable,
			Class<RecruiterDetailsDto> class1);

	
	@Query(value = "SELECT u.email as Email,u.gender as Gender,u.phone_number as PhoneNumber \r\n"
			+ "FROM users u where u.user_type_id=2 and u.is_active=true "
			+ "and (u.email ilike %:search% or u.phone_number ilike %:search%)", nativeQuery = true)
	List<JobSeekerDetailsDto> getAllRegisteredCandidate(String search, Class<JobSeekerDetailsDto> class1);

	

	
	
}
