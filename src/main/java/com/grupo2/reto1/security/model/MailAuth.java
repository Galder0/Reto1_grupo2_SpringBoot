package com.grupo2.reto1.security.model;

public class MailAuth {

	private String oldMail;
	
	private String newMail;
	
	public MailAuth() {
		
	}

	public MailAuth(String oldMail, String newMail) {
		super();
		this.oldMail = oldMail;
		this.newMail = newMail;
	}

	public String getOldMail() {
		return oldMail;
	}

	public void setOldMail(String oldMail) {
		this.oldMail = oldMail;
	}

	public String getNewMail() {
		return newMail;
	}

	public void setNewMail(String newMail) {
		this.newMail = newMail;
	}

	@Override
	public String toString() {
		return "MailAuth [oldMail=" + oldMail + ", newMail=" + newMail + "]";
	}
	
}
