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

import com.revature.exceptions.AuthorizationException;
import com.revature.models.Location;
import com.revature.models.Role;
import com.revature.services.AuthService;
import com.revature.services.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {

	private LocationService lServ;
	private AuthService aServ;
	
	@Autowired
	public LocationController(LocationService lServ, AuthService aServ) {
		super();
		this.lServ = lServ;
		this.aServ = aServ;
	}
	
	@GetMapping
	public ResponseEntity<List<Location>> getLocations() {
		return new ResponseEntity<>(lServ.getLocations(), HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Location> getLocationById(@PathVariable("id") int id) {
		return new ResponseEntity<>(lServ.getLocationById(id), HttpStatus.OK);	
	}
	
	@PostMapping
	public ResponseEntity<Location> postLocation(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody Location location) {
		if (!aServ.checkPermission(token, Role.SELLER, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		Location newLocation = lServ.createLocation(location);
		return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Location> updateLocation(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id, @RequestBody Location location) {
		if (!aServ.checkPermission(token, Role.SELLER, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		location.setId(id);
		return new ResponseEntity<>(lServ.updateLocation(location), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLocation(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id) {
		if (!aServ.checkPermission(token, Role.SELLER, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		lServ.deleteLocation(id);
		return new ResponseEntity<>("Location was deleted.", HttpStatus.OK);
	}

}
