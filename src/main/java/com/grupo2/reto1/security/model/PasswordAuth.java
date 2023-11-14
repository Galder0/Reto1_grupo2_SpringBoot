package com.grupo2.reto1.security.model;

public class PasswordAuth {
	
	private String oldPassword;
	
	private String newPassword;
	
	public PasswordAuth() {
		
	}

	public PasswordAuth(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "PasswordAuth [oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}
	
	
	
	

}
