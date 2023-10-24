package com.grupo2.reto1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Error, Contraint not Found")
public class ConstraintNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public 	ConstraintNotFoundException (String messageError) {
		super(messageError);
	}

}
