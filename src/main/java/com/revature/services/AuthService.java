package com.revature.services;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.config.JwtTokenUtil;
import com.revature.dtos.UserDto;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {

	private UserRepository uRepo;
	private PasswordEncoder passwordEncoder;
	private JwtTokenUtil jUtil;
	private static final Logger Log = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	public AuthService(UserRepository uRepo, PasswordEncoder passwordEncoder, JwtTokenUtil jUtil) {
		super();
		this.uRepo = uRepo;
		this.passwordEncoder = passwordEncoder;
		this.jUtil = jUtil;
	}
	
	public UserDto login(String email, String password) {
		User principal = uRepo.findUserByEmail(email);
		
		if (principal == null || !passwordEncoder.matches(password, principal.getPassword())) {
			throw new AuthenticationException();
		}
		Log.info("Successful login with username " + principal.getEmail());
		return new UserDto(principal);
	}
	
	public String generateToken(UserDto principal) {
		User user = uRepo.getById(principal.getId());
		return jUtil.generateToken(user);
	}
	
	public boolean checkPermission(String token, Role... allowedRoles) {
		if (token == null) {
			throw new AuthorizationException();
		}
		
		String email = jUtil.getEmailFromToken(token);
		Role role = Role.valueOf(jUtil.getRoleFromToken(token).toString());
		User principal = uRepo.findUserByEmail(email);
		MDC.put("principal", email);
		
		if (principal != null && role.equals(principal.getRole()) && Arrays.asList(allowedRoles).contains(role)) {
			return true;
		}
		
		return false;
	}
}
