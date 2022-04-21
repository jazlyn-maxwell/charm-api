package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.LocationNotFoundException;
import com.revature.models.Location;
import com.revature.repositories.LocationRepository;

@Service
public class LocationService {
	
	private LocationRepository lRepo;

	@Autowired
	public LocationService(LocationRepository lRepo) {
		super();
		this.lRepo = lRepo;
	}
	
	public List<Location> getLocations() {
		return lRepo.findAll();
	}
	
	public Location getLocationById(int id) {
		return lRepo.findById(id).orElseThrow(LocationNotFoundException::new);
	}
	
	@Transactional
	public Location createLocation(Location location) {
		return lRepo.save(location);
	}
	
	@Transactional
	public Location updateLocation(Location location) {
		 Location locationUpdate = lRepo.findById(location.getId()).orElseThrow(LocationNotFoundException::new);
		// Validate input
			if (location.getRegion() != null && !location.getRegion().equals(locationUpdate.getRegion())) {
				locationUpdate.setRegion(location.getRegion());
			}
			if (location.getWorld() != null && !location.getWorld().equals(locationUpdate.getWorld())) {
				locationUpdate.setWorld(location.getWorld());
			}
			return lRepo.save(locationUpdate);
	}
	
	@Transactional
	public void deleteLocation(int id) {
		Location location = lRepo.findById(id).orElseThrow(LocationNotFoundException::new);
		lRepo.delete(location);
	}
}
