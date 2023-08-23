package com.recruitment_portal.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recruitment_portal.entities.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Long>  {

	OtpEntity findByEmailIgnoreCase(String email);

	OtpEntity findByOtp(String otp);

	OtpEntity findByEmail(String email);

	@Query(value = "DELETE FROM otp_logger u WHERE u.email=:email", nativeQuery = true)
	@Transactional
	@Modifying
	void deleteAllByEmail(@Param("email") String email);
}
