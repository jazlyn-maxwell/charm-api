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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.CharmDto;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.Charm;
import com.revature.models.Role;
import com.revature.services.AuthService;
import com.revature.services.CharmService;

@RestController
@RequestMapping("/charms")
public class CharmController {

	private CharmService cServ;
	private AuthService aServ;
	
	@Autowired
	public CharmController(CharmService cServ, AuthService aServ) {
		super();
		this.cServ = cServ;
		this.aServ = aServ;
	}
	
	@GetMapping
	public ResponseEntity<List<CharmDto>> getCharms(
			@RequestParam(name="name", required=false) String nameParam,
			@RequestParam(name="description", required=false) String descriptionParam,
			@RequestParam(name="price", required=false) String priceParam){
		int price = 0;
		if (priceParam != null) {
			try {
				price = Integer.parseInt(priceParam);
			}
			catch(NumberFormatException e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		
		if (nameParam != null && descriptionParam != null && priceParam != null) {
			
			return new ResponseEntity<>(cServ.getCharmsByNameAndDescriptionAndPrice(nameParam, descriptionParam, price), HttpStatus.OK);	
		}
		else if (nameParam != null && descriptionParam != null) {
			return new ResponseEntity<>(cServ.getCharmsByNameAndDescription(nameParam, descriptionParam), HttpStatus.OK);	
		}
		else if (nameParam != null && priceParam != null) {
			return new ResponseEntity<>(cServ.getCharmsByNameAndPrice(nameParam, price), HttpStatus.OK);	
		}
		else if (descriptionParam != null && priceParam != null) {

			return new ResponseEntity<>(cServ.getCharmsByDescriptionAndPrice(descriptionParam, price), HttpStatus.OK);	
		}
		else if (nameParam != null) {
			return new ResponseEntity<>(cServ.getCharmsByName(nameParam), HttpStatus.OK);	
		}
		else if (descriptionParam != null) {
			return new ResponseEntity<>(cServ.getCharmsByDescription(descriptionParam), HttpStatus.OK);	
		}
		else if (priceParam != null) {
			return new ResponseEntity<>(cServ.getCharmsByPrice(price), HttpStatus.OK);	
		}
		
		return new ResponseEntity<>(cServ.getCharms(), HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CharmDto> getCharmById(@PathVariable("id") int id) {
		return new ResponseEntity<>(cServ.getCharmById(id), HttpStatus.OK);	
	}
	
	@PostMapping
	public ResponseEntity<CharmDto> postCharm(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody Charm charm) {
		if (!aServ.checkPermission(token, Role.SELLER, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		CharmDto newCharm = cServ.createCharm(charm);
		return new ResponseEntity<>(newCharm, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CharmDto> updateCharm(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id, @RequestBody Charm charm) {
		if (!aServ.checkPermission(token, Role.SELLER, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		charm.setId(id);
		return new ResponseEntity<>(cServ.updateCharm(charm), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCharm(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id) {
		if (!aServ.checkPermission(token, Role.SELLER, Role.ADMIN)) {
			throw new AuthorizationException();
		}
		cServ.deleteCharm(id);
		return new ResponseEntity<>("Charm was deleted.", HttpStatus.OK);
	}
}
