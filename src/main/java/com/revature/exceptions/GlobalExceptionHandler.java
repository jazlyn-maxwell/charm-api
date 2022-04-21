package com.revature.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		LOG.warn("Authentication exception was handled.", e);
		return new ResponseEntity<>("Incorrect credentials", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<String> handleAuthenticationException(AuthorizationException e) {
		LOG.warn("Authorization exception was handled.", e);
		return new ResponseEntity<>("Not Authorized", HttpStatus.UNAUTHORIZED);
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No charm was found with the given parameters.")
	@ExceptionHandler(CharmNotFoundException.class)
	public void handleCharmNotFoundException(CharmNotFoundException e) {
		LOG.warn("Charm not found exception was handled.", e);
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No user was found with the given parameters.")
	@ExceptionHandler(UserNotFoundException.class)
	public void handleUserNotFoundException(UserNotFoundException e) {
		LOG.warn("User not found exception was handled.", e);
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No location was found with the given parameters.")
	@ExceptionHandler(LocationNotFoundException.class)
	public void handleLocationNotFoundException(LocationNotFoundException e) {
		LOG.warn("Location not found exception was handled.", e);
	}

}
