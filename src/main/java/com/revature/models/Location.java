package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="locations")
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String region;
	@Column(nullable=false)
	private String world;
	
	public Location() {
		super();
	}

	public Location(int id, String region, String world) {
		this();
		this.id = id;
		this.region = region;
		this.world = world;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", region=" + region + ", world=" + world + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, region, world);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return id == other.id && Objects.equals(region, other.region) && Objects.equals(world, other.world);
	}
	
}
