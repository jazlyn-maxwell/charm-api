package com.revature.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.revature.models.Charm;

@Repository
public interface CharmRepository extends JpaRepository<Charm, Integer>, JpaSpecificationExecutor<Charm> {

	public Charm findCharmByName(String name);
	
	static Specification<Charm> hasName(String name) {
		return (charm, query, builder) -> builder.equal(charm.get("name"), name);
	}
	
	static Specification<Charm> descriptionContains(String description) {
		return (charm, query, builder) -> builder.like(charm.get("description"), "%" + description + "%");
	}
	
	static Specification<Charm> hasPrice(int price) {
		return (charm, query, builder) -> builder.equal(charm.get("price"), price);
	}
}
