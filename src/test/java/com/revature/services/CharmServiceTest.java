package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.data.jpa.domain.Specification;

import com.revature.dtos.CharmDto;
import com.revature.exceptions.CharmNotFoundException;
import com.revature.models.Charm;
import com.revature.models.Location;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.CharmRepository;

@ExtendWith(MockitoExtension.class)
public class CharmServiceTest {

	static CharmRepository cRepo;
	static CharmService cServ;
	static List<Charm> charms = new ArrayList<>();
	static List<CharmDto> charmDtos = new ArrayList<>();
	static Location location;
	static User user;
	static Charm charm;
	
	@BeforeAll
	public static void init() {
		cRepo = mock(CharmRepository.class);
		cServ = new CharmService(cRepo);
		location = new Location(1, "test", "test");
		user = new User(1, "test", "test@test.test", "password", Role.ADMIN);
		charm = new Charm(1, "test", "info", 101, location, user);
		charms.add(charm);
		charmDtos.add(new CharmDto(charm));
	}
	
	@Test
	public void testGetCharms() {
		when(cRepo.findAll()).thenReturn(charms);
		assertEquals(charmDtos, cServ.getCharms());
		assertEquals(charmDtos.size(), cServ.getCharms().size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCharmsByNameAndDescriptionAndPrice() {
		when(cRepo.findAll(any(Specification.class))).thenReturn(charms);
		assertEquals(charmDtos, cServ.getCharmsByNameAndDescriptionAndPrice("test", "test", 101));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCharmsByNameAndDescription() {
		when(cRepo.findAll(any(Specification.class))).thenReturn(charms);
		assertEquals(charmDtos, cServ.getCharmsByNameAndDescription("test", "test"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCharmsByNameAndPrice() {
		when(cRepo.findAll(any(Specification.class))).thenReturn(charms);
		assertEquals(charmDtos, cServ.getCharmsByNameAndPrice("test", 101));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCharmsByDescriptionAndPrice() {
		when(cRepo.findAll(any(Specification.class))).thenReturn(charms);
		assertEquals(charmDtos, cServ.getCharmsByDescriptionAndPrice("test", 101));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCharmsByName() {
		when(cRepo.findAll(any(Specification.class))).thenReturn(charms);
		assertEquals(charmDtos, cServ.getCharmsByName("test"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCharmsByDescription() {
		when(cRepo.findAll(any(Specification.class))).thenReturn(charms);
		assertEquals(charmDtos, cServ.getCharmsByDescription("test"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCharmsByPrice() {
		when(cRepo.findAll(any(Specification.class))).thenReturn(charms);
		assertEquals(charmDtos, cServ.getCharmsByPrice(101));
	}
	
	@Test
	public void testGetCharmById() {
		when(cRepo.findById(anyInt())).thenReturn(Optional.of(charm));
		assertEquals(new CharmDto(charm), cServ.getCharmById(1));
	}
	
	@Test
	public void testGetCharmByIdNotFound() {
		when(cRepo.findById(anyInt())).thenThrow(new CharmNotFoundException());
		assertThrows(CharmNotFoundException.class, () -> {cServ.getCharmById(3);});
	}
	
	
	@Test
	public void testCreateCharm() {
		when(cRepo.save(any(Charm.class))).thenReturn(charm);
		assertEquals(new CharmDto(charm), cServ.createCharm(charm));
	}
	
	@Test
	public void testUpdateCharm() {
		Location location2 = new Location(2, "test2", "test2");
		Location location3 = new Location(3, "test3", "test3");
		User user2 = new User(2, "test2", "test2@test.test", "password2", Role.SELLER);
		User user3 =new User(3, "test3", "test3@test.test", "password3", Role.SELLER);
		Charm charm2 = new Charm(2, "test2", "info2", 102, location2, user2);
		Charm charm3 = new Charm(2, "test3", "info3", 103, location3, user3);
		
		when(cRepo.findById(anyInt())).thenReturn(Optional.of(charm3));
		when(cRepo.save(any(Charm.class))).thenReturn(charm2);
		assertEquals(new CharmDto(charm2), cServ.updateCharm(charm2));
	}
	
	@Test
	public void testDeleteCharm() {
		when(cRepo.findById(anyInt())).thenReturn(Optional.of(charm));
		doNothing().when(cRepo).delete(any(Charm.class));
		cServ.deleteCharm(1);
		verify(cRepo, times(1)).delete(charm);
	}
}
