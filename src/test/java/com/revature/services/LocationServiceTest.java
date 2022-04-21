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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Location;
import com.revature.repositories.LocationRepository;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

	static LocationRepository lRepo;
	static LocationService lServ;
	static List<Location> locations = new ArrayList<>();
	static Location location;
	
	@BeforeAll
	public static void init() {
		lRepo = mock(LocationRepository.class);
		lServ = new LocationService(lRepo);
		location = new Location(1, "test", "test");
		locations.add(location);
	}
	
	@Test
	public void testGetLocations() {
		when(lRepo.findAll()).thenReturn(locations);
		assertEquals(locations, lServ.getLocations());
	}
	
	@Test
	public void testGetLocationById() {
		when(lRepo.findById(anyInt())).thenReturn(Optional.of(location));
		assertEquals(location, lServ.getLocationById(1));
	}
	
	
	@Test
	public void testCreateLocation() {
		when(lRepo.save(any(Location.class))).thenReturn(location);
		assertEquals(location, lServ.createLocation(location));
	}
	
	@Test
	public void testUpdateLocation() {
		Location location2 = new Location(2, "test2", "test2");
		Location location3 = new Location(3, "test3", "test3");
		
		when(lRepo.findById(anyInt())).thenReturn(Optional.of(location3));
		when(lRepo.save(any(Location.class))).thenReturn(location2);
		assertEquals(location2, lServ.updateLocation(location2));
	}
	
	@Test
	public void testDeleteCharm() {
		when(lRepo.findById(anyInt())).thenReturn(Optional.of(location));
		doNothing().when(lRepo).delete(any(Location.class));
		lServ.deleteLocation(1);
		verify(lRepo, times(1)).delete(location);
	}
}
