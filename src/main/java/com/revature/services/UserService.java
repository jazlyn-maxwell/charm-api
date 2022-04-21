package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDto;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;


@Service
public class UserService {

	private UserRepository uRepo;
	private PasswordEncoder passwordEncoder;
	private static final Logger Log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	public UserService(UserRepository uRepo, PasswordEncoder passwordEncoder) {
		super();
		this.uRepo = uRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public List<UserDto> getUsers() {
		List<User> users = uRepo.findAll();
		return users.stream().map(u -> new UserDto(u)).collect(Collectors.toList());
	}
	
	public UserDto getUserById(int id) {
		return new UserDto(uRepo.findById(id).orElseThrow(UserNotFoundException::new));
	}
	
	@Transactional
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Log.info("User created ", user);
		return uRepo.save(user);
	}
	
	@Transactional
	public User updateUser(User user) {
		 User userUpdate = uRepo.findById(user.getId()).orElseThrow(UserNotFoundException::new);
		// Validate input
			if (user.getName() != null && !user.getName().equals(userUpdate.getName())) {
				userUpdate.setName(user.getName());
			}
			if (user.getEmail() != null && !user.getEmail().equals(userUpdate.getEmail())) {
				userUpdate.setEmail(user.getEmail());
			}
			if (user.getPassword() != null && !user.getPassword().equals(userUpdate.getPassword())) {
				userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			if (user.getRole() != null && !user.getRole().equals(userUpdate.getRole())) {
				userUpdate.setRole(user.getRole());
			}
			Log.info("User updated ", userUpdate);
			return uRepo.save(userUpdate);
	}
	
	@Transactional
	public void deleteUser(int id) {
		User user = uRepo.findById(id).orElseThrow(UserNotFoundException::new);
		Log.info("User deleted ", user);
		uRepo.delete(user);
	}

}
