package com.recruitment_portal.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.recruitment_portal.Exception.ResourceNotFoundException;
import com.recruitment_portal.entities.User;
import com.recruitment_portal.repo.UserRepository;
import com.recruitment_portal.serviceImpl.CustomerUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwttokenUtil;

	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException,ExpiredJwtException{
		final String requestTokenHeader = request.getHeader("Authorization");

		String email = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		try {
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

				jwtToken = requestTokenHeader.substring(7);
				email = jwttokenUtil.getUsernameFromToken(jwtToken);
				User userEntity = userRepository.findByEmailIgnoreCaseAndIsActiveTrue(email);
				if (userEntity == null) {
					throw new ResourceNotFoundException("Invalid user");
				}
			}
		} catch (ExpiredJwtException e) {
			response.setHeader("Error", "JWT expired");
		} catch (Exception e) {
			logger.error(e.getMessage());

		}

		// Once we get the token validate it.
		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.customerUserDetailsService.loadUserByUsername(email);
			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwttokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}

