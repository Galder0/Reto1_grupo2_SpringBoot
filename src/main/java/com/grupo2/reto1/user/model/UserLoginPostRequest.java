package com.grupo2.reto1.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserLoginPostRequest {

	@NotNull(message="User email can't be null.")
    @NotEmpty
    @NotBlank
	private String email;
	
	@NotNull(message="Null password")
    @NotEmpty
    @NotBlank
	private String password;

	public UserLoginPostRequest() {
		super();
	}
	
	public UserLoginPostRequest(@NotNull(message = "User email can't be null.") @NotEmpty @NotBlank String email,
			@NotNull(message = "Null password") @NotEmpty @NotBlank String password) {
		super();
		this.email = email;
		this.password = password;
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
		return "UserLoginPostRequest [email=" + email + ", password=" + password + "]";
	}
}
