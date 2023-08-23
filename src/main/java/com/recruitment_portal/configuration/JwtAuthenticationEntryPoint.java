package com.recruitment_portal.configuration;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		if (response.getHeader("Error") != null && response.getHeader("Error").equals("JWT expired")) {
			response.getOutputStream().println(
					"{\r\n" + "	\"message\": \"Jwt expired\",\r\n" + "	\"msgKey\": \"Jwt expired\"\r\n" + "}");
		} else {

			response.getOutputStream().println("{\r\n" + "	\"message\": \"Unauthorized user\",\r\n"
					+ "	\"msgKey\": \"Unauthorized\"\r\n" + "}");
		}
	}

}
