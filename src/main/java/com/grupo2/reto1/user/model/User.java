package com.grupo2.reto1.user.model;

import java.util.Objects;

import com.grupo2.reto1.song.model.Song;

public class User {
	
private Integer id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	public User () {
		
	}
	
	public User(String name, String surname, String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public User(Integer id, String name, String surname, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", password="
				+ password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, password, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
	}

	

}
