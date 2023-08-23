package com.recruitment_portal.serviceImpl;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment_portal.entities.User;
import com.recruitment_portal.repo.UserRepository;
import com.recruitment_portal.service.UserTypeServiceInterface;
import com.recruitment_portal.util.CacheOperation;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTypeServiceInterface userTypeServiceInterface;
	
	@Autowired
	private CacheOperation cache;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userEntity = new User();
		if (!cache.isKeyExist(email, email)) {
			userEntity = userRepository.findByEmailIgnoreCaseAndIsActiveTrue(email);

			cache.addInCache(email, email, userEntity.toString());
		}else {
			String jsonString =  cache.getFromCache(email, email).toString();
			try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(jsonString, Map.class);
			
			userEntity.setPassword((String) map.get("password"));
			userEntity.setEmail((String) map.get("email"));
			userEntity.setId((Integer) map.get("id"));
			}catch(Exception e) {
				System.out.println("erooooooo");
				e.printStackTrace();
				
			}
		}
		if(userEntity.getEmail().isEmpty()) {
			throw new UsernameNotFoundException("User is not found");
		}
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(),
				getAuthority(userEntity));
		
	
	}


	private ArrayList<SimpleGrantedAuthority> getAuthority(User user) {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

			ArrayList<String> permissions = this.userTypeServiceInterface.getPermissionByUserId(user.getId());

			permissions.forEach(e -> {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + e));

			});

		return authorities;

	}

	
}
