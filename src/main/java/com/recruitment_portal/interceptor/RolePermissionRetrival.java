package com.recruitment_portal.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.recruitment_portal.configuration.JwtTokenUtil;
import com.recruitment_portal.entities.User;
import com.recruitment_portal.repo.UserRepository;
import com.recruitment_portal.repo.UserTypeRepository;

@Component
public class RolePermissionRetrival implements HandlerInterceptor {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String authHeader = request.getHeader("Authorization");
		String tokenString = (null != authHeader) ? authHeader.split(" ")[1] : null;
		if(tokenString != null) {
			final String email = jwtTokenUtil.getUsernameFromToken(tokenString);

			User userEntity = userRepository.findByEmailIgnoreCase(email);
			if (userEntity != null) {
				int roleId = userTypeRepository.getRoleBasedOnUser(userEntity.getId());

				String role = (userTypeRepository.findById(roleId)).getUserTypeName();

				ArrayList<String> permissionNames = userTypeRepository.getPermissionNameBasedOnUser(userEntity.getId());

				request.setAttribute("X-user-permissions", permissionNames);
				request.setAttribute("X-user-roles", role);
				request.setAttribute("X-user-id", userEntity.getId());

			}
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);

	}

}
