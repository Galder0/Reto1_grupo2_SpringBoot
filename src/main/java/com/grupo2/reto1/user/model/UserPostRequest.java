package com.grupo2.reto1.user.model;

import java.util.List;

import com.grupo2.reto1.song.model.Song;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserPostRequest {
	
	@NotNull(message="User name can't be null.")
    @NotEmpty
    @NotBlank
	private String name;
	
	@NotNull(message="User surname can't be null.")
    @NotEmpty
    @NotBlank
	private String surname;
	
	@NotNull(message="User email can't be null.")
    @NotEmpty
    @NotBlank
	private String email;
	
	@NotNull(message="Null password")
    @NotEmpty
    @NotBlank
	private String password;

	public UserPostRequest(@NotNull(message = "User name can't be null.") @NotEmpty @NotBlank String name,
			@NotNull(message = "User surname can't be null.") @NotEmpty @NotBlank String surname,
			@NotNull(message = "User email can't be null.") @NotEmpty @NotBlank String email,
			@NotNull(message = "Null password") @NotEmpty @NotBlank String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
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
		return "UserPostRequest [name=" + name + ", surname=" + surname + ", email=" + email + ", password=" + password
				+ ", favourites=" + "]";
	}
}
