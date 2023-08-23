package com.recruitment_portal.service;

import javax.validation.Valid;

import com.recruitment_portal.Dto.ForgotPasswordConfirmDto;
import com.recruitment_portal.entities.OtpEntity;
import com.recruitment_portal.entities.User;

public interface AuthService {

	void generateOtpAndSendEmail(String email, int id);

	void updateUserWithPassword(@Valid ForgotPasswordConfirmDto forgotPasswordConfirmDto, User userEntity,
			OtpEntity databaseOtpEntity);

	
}
