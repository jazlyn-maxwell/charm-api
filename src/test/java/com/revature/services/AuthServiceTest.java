package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.revature.config.JwtTokenUtil;
import com.revature.dtos.UserDto;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

public class AuthServiceTest {
	
	static UserRepository uRepo;
	static PasswordEncoder passwordEncoder;
	static JwtTokenUtil jUtil;
	static AuthService aServ;
	static User principal;
	static UserDto principalDto;
	
	@BeforeAll
	public static void init() {
		uRepo = mock(UserRepository.class);
		passwordEncoder = mock(PasswordEncoder.class);
		jUtil = mock(JwtTokenUtil.class);
		aServ = new AuthService(uRepo, passwordEncoder, jUtil);
		principal = new User(1, "test", "test@test.test", "password", Role.ADMIN);
		principalDto = new UserDto(principal);
	}
	
	@Test
	public void testLogin() {
		when(uRepo.findUserByEmail(anyString())).thenReturn(principal);
		when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
		assertEquals(principalDto, aServ.login("test@test.test", "test"));
	}
	
	@Test
	public void testLoginWrongPassword() {
		when(uRepo.findUserByEmail(anyString())).thenReturn(principal);
		when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
		assertThrows(AuthenticationException.class, () -> {aServ.login("test@test.test", "test");});
	}
	
	@Test
	public void testGenerateToken() {
		when(uRepo.getById(anyInt())).thenReturn(principal);
		when(jUtil.generateToken(principal)).thenReturn("token");
		assertEquals("token", aServ.generateToken(principalDto));
	}
	
	@Test
	public void testCheckPermission() {
		when(jUtil.getEmailFromToken(anyString())).thenReturn("test@test.test");
		when(jUtil.getRoleFromToken(anyString())).thenReturn("ADMIN");
		when(uRepo.findUserByEmail(anyString())).thenReturn(principal);
		assertEquals(true, aServ.checkPermission("token", Role.ADMIN));
	}
	
	@Test
	public void testCheckPermissionNullToken() {
		assertThrows(AuthorizationException.class, () -> {aServ.checkPermission(null, Role.ADMIN);});
	}
	
	@Test
	public void testCheckPermissionWrongRole() {
		when(jUtil.getEmailFromToken(anyString())).thenReturn("test@test.test");
		when(jUtil.getRoleFromToken(anyString())).thenReturn("SELLER");
		when(uRepo.findUserByEmail(anyString())).thenReturn(principal);
		assertEquals(false, aServ.checkPermission("token", Role.ADMIN));
	}
	
}
