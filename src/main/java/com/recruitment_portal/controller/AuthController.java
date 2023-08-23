package com.recruitment_portal.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_portal.Dto.ErrorResponseDto;
import com.recruitment_portal.Dto.ForgotPasswordConfirmDto;
import com.recruitment_portal.Dto.OnboardDto;
import com.recruitment_portal.Dto.OtpDto;
import com.recruitment_portal.Dto.SuccessResponseDto;
import com.recruitment_portal.entities.InviteEntity;
import com.recruitment_portal.entities.OtpEntity;
import com.recruitment_portal.entities.User;
import com.recruitment_portal.repo.InviteReposiotry;
import com.recruitment_portal.repo.OtpRepository;
import com.recruitment_portal.repo.UserRepository;
import com.recruitment_portal.service.AuthService;
import com.recruitment_portal.util.ErrorKeyConstant;
import com.recruitment_portal.util.ErrorMessageConstant;
import com.recruitment_portal.util.SuccessKeyConstant;
import com.recruitment_portal.util.SuccessMessageConstant;

@RestController
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private InviteReposiotry inviteReposiotry;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody OtpDto otpDto, HttpServletRequest request) throws Exception {

		try {

			User userEntity = this.userRepo.findByEmailIgnoreCase(otpDto.getEmail());

			if (null == userEntity) {
				return new ResponseEntity<>(
						new ErrorResponseDto(ErrorMessageConstant.USER_NOT_FOUND, ErrorKeyConstant.USER_E031203),
						HttpStatus.BAD_REQUEST);
			}
			authService.generateOtpAndSendEmail(otpDto.getEmail(), userEntity.getId());

			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageConstant.OTP_SENT,
					SuccessKeyConstant.USER_M031204, userEntity.getEmail()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.USER_NOT_FOUND, ErrorKeyConstant.USER_E031203),
					HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("/forgot-password-confirm")
	public ResponseEntity<?> createForgotPasswordConfirm(
			@Valid @RequestBody ForgotPasswordConfirmDto forgotPasswordConfirmDto) throws Exception {

		try {

			String otp = forgotPasswordConfirmDto.getOtp();

			User userEntity = this.userRepo.findByEmailContainingIgnoreCase(forgotPasswordConfirmDto.getEmail());

			if (userEntity == null) {
				return new ResponseEntity<>(
						new ErrorResponseDto(ErrorMessageConstant.USER_NOT_FOUND, ErrorKeyConstant.USER_E031203),
						HttpStatus.BAD_REQUEST);
			}

			OtpEntity databaseOtpEntity = this.otpRepository.findByOtp(forgotPasswordConfirmDto.getOtp());
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());

			if (databaseOtpEntity == null) {
				return new ResponseEntity<>(
						new ErrorResponseDto(ErrorMessageConstant.INVALID_OTP, ErrorKeyConstant.USER_E031205),
						HttpStatus.BAD_REQUEST);
			} else {
				if (!databaseOtpEntity.getEmail().equals(forgotPasswordConfirmDto.getEmail())
						&& ts.compareTo(databaseOtpEntity.getExpireAt()) == -1) {
					return new ResponseEntity<>(
							new ErrorResponseDto(ErrorMessageConstant.INVALID_OTP, ErrorKeyConstant.USER_E031205),
							HttpStatus.BAD_REQUEST);
				}
			}

			this.authService.updateUserWithPassword(forgotPasswordConfirmDto, userEntity, databaseOtpEntity);

			return new ResponseEntity<>(
					new SuccessResponseDto(SuccessMessageConstant.PASSWORD_UPDATED, SuccessKeyConstant.USER_M031205),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.USER_NOT_FOUND, ErrorKeyConstant.USER_E031203),
					HttpStatus.BAD_REQUEST);
		}
	}

	
	@PostMapping("/onboard")
	public ResponseEntity<?> onboard(@RequestBody OnboardDto onboardRequestDto) {
		if (onboardRequestDto.getUuid() == null) {
			return new ResponseEntity<>(
					new SuccessResponseDto(ErrorMessageConstant.INVALID_UUID, ErrorKeyConstant.USER_E031207),
					HttpStatus.BAD_REQUEST);
		}

		InviteEntity inviteEntity = this.inviteReposiotry.findbyUuid(onboardRequestDto.getUuid());

		if (inviteEntity != null) {

			if (onboardRequestDto.getPassword().equals(onboardRequestDto.getConfirmPassword())) {

				User userEntity = this.userRepo.findById(inviteEntity.getUserId()).get();
				userEntity.setPassword(passwordEncoder.encode(onboardRequestDto.getConfirmPassword()));
				this.userRepo.save(userEntity);

				inviteReposiotry.delete(inviteEntity);

				return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageConstant.ONBOARDING_SUCCESSFUL,
						SuccessKeyConstant.USER_M031206), HttpStatus.OK);

			}

			else {
				return new ResponseEntity<>(new ErrorResponseDto(ErrorMessageConstant.PASSWORD_DOES_NOT_MATCH,
						ErrorKeyConstant.USER_E031208), HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<>(
				new ErrorResponseDto(ErrorMessageConstant.INVALID_CODE, ErrorKeyConstant.USER_E031209),
				HttpStatus.BAD_REQUEST);
	}

}
