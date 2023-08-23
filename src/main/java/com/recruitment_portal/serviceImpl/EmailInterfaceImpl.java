package com.recruitment_portal.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.recruitment_portal.IDto.JobPostActivityDto;
import com.recruitment_portal.repo.JobPostRepo;

@Service
public class EmailInterfaceImpl implements EmailInterface {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}") 
	private String sender;
	
	@Autowired
	private JobPostRepo jobPostRepo;
	
	@Override
	public void sendSimpleMailToJobSeeker(int applied, String email) {
		
		SimpleMailMessage mailMassage=new SimpleMailMessage();
		mailMassage.setFrom(sender);
		mailMassage.setTo(email);
     	mailMassage.setSubject("You applied for "+applied+" jobs"+ " on "+ LocalDate.now());
     	mailMassage.setText("Applications are on the way, Good luck!");
     	javaMailSender.send(mailMassage);
		
     	
	}

	@Override
	public void sendSimpleMailToRecruiter(List<Integer> appliedJobPostId,int loggedInUserId) {
		List<JobPostActivityDto> detailsOfAppliedJobPost = jobPostRepo.getDetailsOfAppliedJobPost(appliedJobPostId);
		for(int i=0;i<detailsOfAppliedJobPost.size();i++){
			SimpleMailMessage mailMassage=new SimpleMailMessage();
			mailMassage.setFrom(sender);
			mailMassage.setTo(detailsOfAppliedJobPost.get(i).getRecruiterEmail());
	     	mailMassage.setSubject("UserId: "+loggedInUserId+" applied for "+detailsOfAppliedJobPost.get(i).getAppliedCountPerRecruiter()+" jobs"+ " on "+ LocalDate.now());
	     	mailMassage.setText("Dear Recruiter \n"
	     			           + "	 "+"UserId: "+loggedInUserId+" applied for "+detailsOfAppliedJobPost.get(i).getAppliedCountPerRecruiter()+" jobs, "
	     			           +"He is applied for followed mention job roles: "+detailsOfAppliedJobPost.get(i).getAppliedJobRoles()
	     			           + "\n Please process as early as possible");
	     	javaMailSender.send(mailMassage);
			
			}
		}

	@Override
	public void sendSimpleMailToRecruiter(String email, String baseUrl) {
		SimpleMailMessage mailMassage=new SimpleMailMessage();
		mailMassage.setFrom(sender);
		mailMassage.setTo(email);
     	mailMassage.setSubject("Onboarding mail for Recruitment portal\n");
     	mailMassage.setText("	"+"Your UUID for set password is :- "+baseUrl+ "\nGood luck!");
     	javaMailSender.send(mailMassage);
		
	}

}
