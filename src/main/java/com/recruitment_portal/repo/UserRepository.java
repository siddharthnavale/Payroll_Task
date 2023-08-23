package com.recruitment_portal.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recruitment_portal.Dto.RecruiterDetailsDto;
import com.recruitment_portal.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmailIgnoreCase(String email);
	
	User findByEmailIgnoreCaseAndIsActiveTrue(String email);

	@Transactional
	@Modifying
	@Query(value = "update users set is_active=false where id=:id and user_type_id=2", nativeQuery = true)
	void deleteRegisterCandidate(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value = "update users set is_active=false where id=:id and user_type_id=3", nativeQuery = true)
	void deleteRecruiter(@Param("id") int id);

	@Query(value = "SELECT u.email as Email,u.gender as Gender,u.phone_number as PhoneNumber \r\n"
			+ "FROM users u where u.user_type_id=3 and u.is_active=true "
			+ "and (u.email ilike %:search% or u.phone_number ilike %:search%)", nativeQuery = true)
	List<RecruiterDetailsDto> getAllRegisteredRecruiter(String search, Class<RecruiterDetailsDto> class1);

	User findByEmailContainingIgnoreCase(String email);
}
