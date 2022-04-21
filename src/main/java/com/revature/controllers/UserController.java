package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDto;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService uServ;
	private AuthService aServ;
	
	@Autowired
	public UserController(UserService uServ, AuthService aServ) {
		super();
		this.uServ = uServ;
		this.aServ = aServ;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers(@RequestHeader(value = "Authorization", required = false) String token) {
		if (!aServ.checkPermission(token, Role.SELLER, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		return new ResponseEntity<>(uServ.getUsers(), HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id) {
		if (!aServ.checkPermission(token, Role.SELLER, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		return new ResponseEntity<>(uServ.getUserById(id), HttpStatus.OK);	
	}
	
	@PostMapping
	public ResponseEntity<User> postUser(@RequestBody User user) {
		User newUser = uServ.createUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id, @RequestBody User user) {
		if (!aServ.checkPermission(token, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		user.setId(id);
		return new ResponseEntity<>(uServ.updateUser(user), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id) {
		if (!aServ.checkPermission(token, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		uServ.deleteUser(id);
		return new ResponseEntity<>("User was deleted.", HttpStatus.OK);
	}
}
