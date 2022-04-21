package com.revature.services;

import static com.revature.repositories.CharmRepository.descriptionContains;
import static com.revature.repositories.CharmRepository.hasName;
import static com.revature.repositories.CharmRepository.hasPrice;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.CharmDto;
import com.revature.exceptions.CharmNotFoundException;
import com.revature.models.Charm;
import com.revature.repositories.CharmRepository;

@Service
public class CharmService {

	private CharmRepository cRepo;
	
	@Autowired
	public CharmService(CharmRepository cRepo) {
		super();
		this.cRepo = cRepo;
	}
	
	public List<CharmDto> getCharms() {
		List<Charm> charms = cRepo.findAll();
		return charms.stream().map(c -> new CharmDto(c)).collect(Collectors.toList());
	}

	public List<CharmDto> getCharmsByNameAndDescriptionAndPrice(String name, String description, int price) {
		List<Charm> charms = cRepo.findAll(where(hasName(name)).and(descriptionContains(description)).and(hasPrice(price)));
		return charms.stream().map(c -> new CharmDto(c)).collect(Collectors.toList());
	}

	public List<CharmDto> getCharmsByNameAndDescription(String name, String description) {
		List<Charm> charms = cRepo.findAll(where(hasName(name)).and(descriptionContains(description)));
		return charms.stream().map(c -> new CharmDto(c)).collect(Collectors.toList());
	}

	public List<CharmDto> getCharmsByNameAndPrice(String name, int price) {
		List<Charm> charms = cRepo.findAll(where(hasName(name)).and(hasPrice(price)));
		return charms.stream().map(c -> new CharmDto(c)).collect(Collectors.toList());
	}

	public List<CharmDto> getCharmsByDescriptionAndPrice(String description, int price) {
		List<Charm> charms = cRepo.findAll(where(descriptionContains(description)).and(hasPrice(price)));
		return charms.stream().map(c -> new CharmDto(c)).collect(Collectors.toList());
	}

	public List<CharmDto> getCharmsByName(String name) {
		List<Charm> charms = cRepo.findAll(where(hasName(name)));
		return charms.stream().map(c -> new CharmDto(c)).collect(Collectors.toList());
	}

	public List<CharmDto> getCharmsByDescription(String description) {
		List<Charm> charms = cRepo.findAll(where(descriptionContains(description)));
		return charms.stream().map(c -> new CharmDto(c)).collect(Collectors.toList());
	}

	public List<CharmDto> getCharmsByPrice(int price) {
		List<Charm> charms = cRepo.findAll(where(hasPrice(price)));
		return charms.stream().map(c -> new CharmDto(c)).collect(Collectors.toList());
	}
	
	public CharmDto getCharmById(int id) {
		Charm charm = cRepo.findById(id).orElseThrow(CharmNotFoundException::new);
		return new CharmDto(charm);
	}
	
	@Transactional
	public CharmDto createCharm(Charm charm) {
		return new CharmDto(cRepo.save(charm));
	}
	
	@Transactional
	public CharmDto updateCharm(Charm charm) {
		 Charm charmUpdate = cRepo.findById(charm.getId()).orElseThrow(CharmNotFoundException::new);
		// Validate input
			if (charm.getName() != null && !charm.getName().equals(charmUpdate.getName())) {
				charmUpdate.setName(charm.getName());
			}
			if (charm.getDescription() != null && !charm.getDescription().equals(charmUpdate.getDescription())) {
				charmUpdate.setDescription(charm.getDescription());
			}
			if (charm.getPrice() != charmUpdate.getPrice()) {
				charmUpdate.setPrice(charm.getPrice());
			}
			if (charm.getLocation() != null && charm.getLocation().getId() != charmUpdate.getLocation().getId()) {
				charmUpdate.setLocation(charm.getLocation());
			}
			if (charm.getSeller() != null && charm.getSeller().getId() != charmUpdate.getSeller().getId()) {
				charmUpdate.getSeller().setId(charm.getSeller().getId());
			}
			return new CharmDto(cRepo.save(charmUpdate));
	}
	
	@Transactional
	public void deleteCharm(int id) {
		Charm charm = cRepo.findById(id).orElseThrow(CharmNotFoundException::new);
		cRepo.delete(charm);
	}
	
	
}
