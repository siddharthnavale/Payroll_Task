package com.recruitment_portal.service;

import java.util.Date;

public interface OtpInterface {


	void saveOtp(String email, String otp1, int userId, Date time);

	

}
