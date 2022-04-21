package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="charms")
public class Charm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String description;
	@Column(nullable=false)
	private int price;
	@ManyToOne
	@JoinColumn(nullable=false)
	private Location location;
	@ManyToOne
	@JoinColumn(nullable=false)
	private User seller;
	
	public Charm() {
		super();
	}

	public Charm(int id, String name, String description, int price, Location location, User seller) {
		this();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.location = location;
		this.seller = seller;
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

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Charm [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
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
		Charm other = (Charm) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name) && price == other.price
				&& Objects.equals(seller, other.seller);
	}
		
}
