package com.grupo2.reto1.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserPostRequest;
import com.grupo2.reto1.user.model.UserServiceResponse;
import com.grupo2.reto1.user.service.UserService;

import jakarta.validation.Valid;

@RequestMapping("api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserServiceResponse>> getUsers()  {
		return new ResponseEntity<List<UserServiceResponse>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserServiceResponse> getUser(@PathVariable("id") Integer id) {
		return new ResponseEntity<UserServiceResponse>(userService.getUsersById(id), HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<Integer> createUser(@Valid @RequestBody UserPostRequest userDTO) {
		User user = new User(userDTO.getEmail(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPassword());
		return new ResponseEntity<Integer>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Integer> updateUser(@PathVariable("id") Integer id, @Valid @RequestBody UserPostRequest userDTO) {
		User user = new User(id, userDTO.getEmail(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPassword());
		return new ResponseEntity<Integer>(userService.updateUser(user), HttpStatus.CREATED);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Integer> deleteUser(@PathVariable("id") Integer id) {
		return new ResponseEntity<Integer>(userService.deleteUserById(id), HttpStatus.OK);
	}
}
