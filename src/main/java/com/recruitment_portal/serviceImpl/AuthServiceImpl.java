package com.recruitment_portal.serviceImpl;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recruitment_portal.Dto.ForgotPasswordConfirmDto;
import com.recruitment_portal.entities.OtpEntity;
import com.recruitment_portal.entities.User;
import com.recruitment_portal.repo.OtpRepository;
import com.recruitment_portal.repo.UserRepository;
import com.recruitment_portal.service.AuthService;
import com.recruitment_portal.service.OtpInterface;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private OtpInterface otpInterface;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}") 
	private String sender;
	
	public int generateOTP() {

		int min = 100000;
		int max = 999999;

		int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);
		return randomInt;

	}
	
	private void sendSimpleMessage(String email, String otp) {
		SimpleMailMessage mailMassage=new SimpleMailMessage();
		mailMassage.setFrom(sender);
		mailMassage.setTo(email);
     	mailMassage.setSubject("OTP for Recruitment portal");
     	mailMassage.setText("Dear User \n"
     			           + "Your OTP is "+otp);
     	javaMailSender.send(mailMassage);
		
	}
	
	public void generateOtpAndSendEmail(String email, int userId)  {

		final int otp =generateOTP();
		
		String otp1 = Integer.toString(otp);

		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.MINUTE, 5);

		this.otpInterface.saveOtp(email, otp1, userId, calender.getTime());

		sendSimpleMessage(email,otp1);

	}

	@Override
	public void updateUserWithPassword(ForgotPasswordConfirmDto forgotPasswordConfirmDto, User userEntity,
			OtpEntity databaseOtpEntity) {
		userEntity.setPassword(passwordEncoder.encode(forgotPasswordConfirmDto.getPassword()));
		this.userRepository.save(userEntity);
		this.otpRepository.delete(databaseOtpEntity);
		
	}

	
	
}
