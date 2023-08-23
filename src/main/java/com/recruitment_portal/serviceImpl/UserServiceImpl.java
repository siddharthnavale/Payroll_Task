package com.recruitment_portal.serviceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recruitment_portal.Dto.JobSeekerDetailsDto;
import com.recruitment_portal.Dto.RecruiterAddDto;
import com.recruitment_portal.Dto.RecruiterDetailsDto;
import com.recruitment_portal.Dto.UserDtoRequest;
import com.recruitment_portal.Exception.DuringStoringPhotosException;
import com.recruitment_portal.IDto.IJobPostDto;
import com.recruitment_portal.entities.GenderEnum;
import com.recruitment_portal.entities.User;
import com.recruitment_portal.entities.UserType;
import com.recruitment_portal.repo.UserRepository;
import com.recruitment_portal.repo.UserTypeRepository;
import com.recruitment_portal.service.InviteServiceInterface;
import com.recruitment_portal.service.UserService;
import com.recruitment_portal.util.ErrorMessageConstant;
import com.recruitment_portal.util.Pagination;

@Service
public class UserServiceImpl implements UserService {
   
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bCryptEncoder;
	
	@Autowired
	private InviteServiceInterface inviteServiceInterface;
	
	@Autowired
	private EmailInterface emailInterface;
	
	@Override
	public void addUser(@Valid UserDtoRequest userDtoRequest,MultipartFile file) {
	
		User user=new User();
		user.setBirthDate(LocalDate.parse(userDtoRequest.getDateOfBirth()));
		user.setEmail(userDtoRequest.getEmail());
		user.setGender(Enum.valueOf(GenderEnum.class,userDtoRequest.getGender()));
		try {
			user.setImageData(file.getBytes());
			}catch (IOException ex) {

				throw new DuringStoringPhotosException("File not Stored.. Please try again......", ex);
			}
		user.setPassword(bCryptEncoder.encode(userDtoRequest.getPassword()));
		user.setPhoneNumber(userDtoRequest.getPhoneNumber());
		
		UserType userType=userTypeRepository.findById(Integer.parseInt((userDtoRequest.getUserType())));
		user.setUserType(userType);
		userRepository.save(user);
	}

	@Override
	public Page<JobSeekerDetailsDto> getAllRegisteredCandidate(String search, String pageNo, String pageSize) {
		Pageable pageable =new Pagination().getPagination(pageNo,pageSize);
		Page<JobSeekerDetailsDto> page=userTypeRepository.getAllRegisteredCandidate(search,pageable,JobSeekerDetailsDto.class);
		return page;
	}

	@Override
	public Page<RecruiterDetailsDto> getAllRegisteredRecruiter(String search, String pageNo, String pageSize) {
		Pageable pageable =new Pagination().getPagination(pageNo,pageSize);
		Page<RecruiterDetailsDto> page=userTypeRepository.getAllRegisteredRecruiter(search,pageable,RecruiterDetailsDto.class);
		return page;
	}

	@Override
	public void deleteRegisteredCandidate(int id) throws Exception {
		this.userRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessageConstant.USER_NOT_FOUND));
		this.userRepository.deleteRegisterCandidate(id);
	}

	@Override
	public void deleteRecruiter(int id) throws Exception {
		this.userRepository.findById(id).orElseThrow(() -> new Exception(ErrorMessageConstant.USER_NOT_FOUND));
		this.userRepository.deleteRecruiter(id);
		
	}

	@Override
	public List<JobSeekerDetailsDto> getAllRegisteredCandidate(String search) {
		List<JobSeekerDetailsDto> allRegisteredCandidate =userTypeRepository.getAllRegisteredCandidate(search,JobSeekerDetailsDto.class);
		return allRegisteredCandidate;
	}

	@Override
	public void exportAllRegisteredCandidate(HttpServletResponse response, List<JobSeekerDetailsDto> jobseekers) throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("Email").append(",").append("Gender")
				.append(",").append("PhoneNumber");
		builder.append('\n');
	
		for (JobSeekerDetailsDto jobseeker : jobseekers) {
			builder.append(jobseeker.getEmail() != null ? jobseeker.getEmail() : "").append(",")
					.append(jobseeker.getGender() != null ? jobseeker.getGender() : "").append(",")
					.append(jobseeker.getPhoneNumber() != null ? jobseeker.getPhoneNumber()  : "");

			builder.append('\n');
		}

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=AllRegisteredCandidate.csv");

		PrintWriter writer = response.getWriter();
		writer.write(builder.toString());
		writer.flush();
		writer.close();
		
		
	}

	@Override
	public List<RecruiterDetailsDto> getAllRegisteredRecruiter(String search) {
		List<RecruiterDetailsDto> allRegisteredRecruiter =userRepository.getAllRegisteredRecruiter(search,RecruiterDetailsDto.class);
		return allRegisteredRecruiter;
	}

	@Override
	public void exportAllRegisteredRecruiter(HttpServletResponse response, List<RecruiterDetailsDto> recruiters) throws IOException {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Email").append(",").append("Gender")
				.append(",").append("PhoneNumber");
		builder.append('\n');
	
		for (RecruiterDetailsDto recruiter : recruiters) {
			builder.append(recruiter.getEmail() != null ? recruiter.getEmail() : "").append(",")
					.append(recruiter.getGender() != null ? recruiter.getGender() : "").append(",")
					.append(recruiter.getPhoneNumber() != null ? recruiter.getPhoneNumber()  : "");

			builder.append('\n');
		}

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=AllRegisteredCandidate.csv");

		PrintWriter writer = response.getWriter();
		writer.write(builder.toString());
		writer.flush();
		writer.close();
		
	}

	@Override
	public RecruiterAddDto addUser(RecruiterAddDto recruiterAddDto, int loggedInUserId, MultipartFile file) {
		User user=new User();
		user.setBirthDate(LocalDate.parse(recruiterAddDto.getDateOfBirth()));
		user.setEmail(recruiterAddDto.getEmail());
		user.setGender(Enum.valueOf(GenderEnum.class,recruiterAddDto.getGender()));
		try {
			user.setImageData(file.getBytes());
			}catch (IOException ex) {

				throw new DuringStoringPhotosException("File not Stored.. Please try again......", ex);
			}
		user.setPhoneNumber(recruiterAddDto.getPhoneNumber());
		
		UserType userType=userTypeRepository.findById(3);
		user.setUserType(userType);
		user.setCreatedBy(loggedInUserId);
		userRepository.save(user);
		
		UUID uuid = UUID.randomUUID();
		
		inviteServiceInterface.add(uuid, user.getId());
		
		String uuidstr = "" + uuid;
		
		emailInterface.sendSimpleMailToRecruiter(user.getEmail(),uuidstr);
		
		return recruiterAddDto;
	}

}
