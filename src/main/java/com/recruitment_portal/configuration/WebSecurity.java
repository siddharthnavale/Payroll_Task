package com.recruitment_portal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.recruitment_portal.serviceImpl.CustomerUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

	
	public static final String [] PUBLIC_URLS= {
			"/user/sign-up",
			"/jwt/generateToken",
			"/forgot-password",
			"/forgot-password-confirm",
			"/onboard","/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
	};

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;


	@Autowired
	private CustomerUserDetailsService customUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
		  @Override 
		  protected void configure(HttpSecurity http) throws Exception {
			  
			  http.csrf().disable()
			  .authorizeRequests().antMatchers(PUBLIC_URLS).permitAll()
			  .anyRequest().authenticated().and().exceptionHandling()
			  .authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
			  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			  http.addFilterBefore(jwtRequestFilter,
			  UsernamePasswordAuthenticationFilter.class); 
			  }

		 
	}


