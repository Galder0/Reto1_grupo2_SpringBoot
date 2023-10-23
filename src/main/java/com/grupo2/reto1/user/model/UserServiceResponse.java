package com.grupo2.reto1.user.model;

import java.util.List;
import com.grupo2.reto1.song.model.SongServiceResponse;

public class UserServiceResponse {
	
	private Integer id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private List<SongServiceResponse> favourites;
	
	public UserServiceResponse() {
		
	}
	
	public UserServiceResponse(String name, String surname, String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	
	public UserServiceResponse(Integer id, String name, String surname, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public UserServiceResponse(Integer id, String name, String surname, String email, String password,
			List<SongServiceResponse> favourites) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.favourites = favourites;
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

	public List<SongServiceResponse> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<SongServiceResponse> favourites) {
		this.favourites = favourites;
	}

	@Override
	public String toString() {
		return "UserServiceResponse [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", password=" + password + ", favourites=" + favourites + "]";
	}

}
