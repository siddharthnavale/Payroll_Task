package com.recruitment_portal.serviceImpl;

import java.util.List;

public interface EmailInterface {

	void sendSimpleMailToJobSeeker(int applied, String email);

	void sendSimpleMailToRecruiter(List<Integer> appliedJobPostId, int loggedInUserId);

	void sendSimpleMailToRecruiter(String email, String baseUrl);

}
