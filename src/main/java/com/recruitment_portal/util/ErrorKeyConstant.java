package com.recruitment_portal.util;

public class ErrorKeyConstant {
	public static final String USERTYPE = "11";
	public static final String USER = "12";
	public static final String JOB_POST_ACTIVITY = "13";
	public static final String COMPANY = "14";



	public static final String USERTYPE_E031101 = "RP-E03" + USERTYPE + "01"; // role already present
	
	public static final String USER_E031201 = "RP-E03" + USER + "01"; 
	public static final String USER_E031202 = "RP-E03" + USER + "02";//Invalid User
	public static final String USER_E031203 = "RP-E03" + USER + "03";// User not found
	public static final String USER_E031204 = "RP-E03" + USER + "04"; // invalid email
	public static final String USER_E031205 = "RP-E03" + USER + "05"; // invalid otp
	public static final String USER_E031206 = "RP-E03" + USER + "06"; // user already esists
	public static final String USER_E031207 = "RP-E03" + USER + "07";// invalid uuid
	public static final String USER_E031208 = "RP-E03" + USER + "08"; // invalid password
	public static final String USER_E031209 = "RP-E03" + USER + "09";// invalid code
	
	
	public static final String JOB_POST_E031301 = "RP-E03" + JOB_POST_ACTIVITY + "01";// Job Post not found

	public static final String COMPANY_E031401 = "RP-E03" + COMPANY + "01";// Company not found

}
