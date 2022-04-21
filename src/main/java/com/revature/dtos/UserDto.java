package com.revature.dtos;

import java.util.Objects;

import com.revature.models.User;

public class UserDto {

	private int id;
	private String name;
	private String email;
	
	public UserDto() {
		super();
	}

	public UserDto(User user) {
		this();
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name);
	}
	
}
