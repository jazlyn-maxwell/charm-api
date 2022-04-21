package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.revature.dtos.UserDto;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

public class UserServiceTest {

	static UserRepository uRepo;
	static PasswordEncoder passwordEncoder;
	static UserService uServ;
	static List<User> users = new ArrayList<>();
	static List<UserDto> userDtos = new ArrayList<>();
	static UserDto userDto;
	static User user;
	
	@BeforeAll
	public static void init() {
		uRepo = mock(UserRepository.class);
		passwordEncoder = mock(PasswordEncoder.class);
		uServ = new UserService(uRepo, passwordEncoder);
		user = new User(1, "test", "test@test.test", "password", Role.ADMIN);
		userDto = new UserDto(user);
		users.add(user);
		userDtos.add(userDto);
	}
	
	@Test
	public void testGetUsers() {
		when(uRepo.findAll()).thenReturn(users);
		assertEquals(userDtos, uServ.getUsers());
	}
	
	@Test
	public void testGetUserById() {
		when(uRepo.findById(anyInt())).thenReturn(Optional.of(user));
		assertEquals(userDto, uServ.getUserById(1));
	}
	
	
	@Test
	public void testCreateUser() {
		when(uRepo.save(any(User.class))).thenReturn(user);
		assertEquals(user, uServ.createUser(user));
	}
	
	@Test
	public void testUpdateUser() {
		User user2 = new User(2, "test2", "test2@test.test", "password2", Role.ADMIN);
		User user3 =new User(3, "test3", "test3@test.test", "password3", Role.SELLER);
		
		when(uRepo.findById(anyInt())).thenReturn(Optional.of(user3));
		when(uRepo.save(any(User.class))).thenReturn(user2);
		assertEquals(user2, uServ.updateUser(user2));
	}
	
	@Test
	public void testDeleteCharm() {
		when(uRepo.findById(anyInt())).thenReturn(Optional.of(user));
		doNothing().when(uRepo).delete(any(User.class));
		uServ.deleteUser(1);
		verify(uRepo, times(1)).delete(user);
	}

}
