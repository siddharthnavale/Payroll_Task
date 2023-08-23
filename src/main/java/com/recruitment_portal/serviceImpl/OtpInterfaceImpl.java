package com.recruitment_portal.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitment_portal.entities.OtpEntity;
import com.recruitment_portal.repo.OtpRepository;
import com.recruitment_portal.service.OtpInterface;

@Service
public class OtpInterfaceImpl implements OtpInterface {

	@Autowired
	private OtpRepository otpRepository;

	@Override
	public void saveOtp(String email, String otp, int userId, Date expiry) {
		otpRepository.deleteAllByEmail(email);

		OtpEntity entities = new OtpEntity();
		entities.setUserId(userId);
		entities.setEmail(email.toLowerCase());
		entities.setOtp(otp);
		entities.setExpireAt(expiry);
		otpRepository.save(entities);

	}

}
