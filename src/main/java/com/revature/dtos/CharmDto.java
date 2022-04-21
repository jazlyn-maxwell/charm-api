package com.revature.dtos;

import java.util.Objects;

import com.revature.models.Charm;
import com.revature.models.Location;

public class CharmDto {
	
	private int id;
	private String name;
	private String description;
	private int price;
	private Location location;
	private UserDto seller;
	
	public CharmDto() {
		super();
	}
	
	public CharmDto(Charm charm) {
		this.id = charm.getId();
		this.name = charm.getName();
		this.description = charm.getDescription();
		this.price = charm.getPrice();
		this.location = charm.getLocation();
		this.seller = new UserDto(charm.getSeller());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public UserDto getSeller() {
		return seller;
	}

	public void setSeller(UserDto seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "CharmDto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", location=" + location + ", seller=" + seller + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, location, name, price, seller);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CharmDto other = (CharmDto) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name) && price == other.price
				&& Objects.equals(seller, other.seller);
	}
	
}
