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
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.reto1.user.model.UserPostRequest;
import com.grupo2.reto1.user.model.UserServiceResponse;
import com.grupo2.reto1.user.service.UserService;

import jakarta.validation.Valid;

@RequestMapping("/api/users")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserServiceResponse>> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserServiceResponse> getUserById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity<Integer> createUser(@Valid @RequestBody UserPostRequest userPostRequest){
		UserServiceResponse userServiceResponse = new UserServiceResponse(userPostRequest.getName(), userPostRequest.getSurname(), userPostRequest.getEmail(), userPostRequest.getPassword());
		return new ResponseEntity<> (userService.createUser(userServiceResponse), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> updateUser(@PathVariable("id") Integer id, @RequestBody UserPostRequest userPostRequest){
		UserServiceResponse userServiceResponse = new UserServiceResponse(userPostRequest.getName(), userPostRequest.getSurname(), userPostRequest.getEmail(), userPostRequest.getPassword());
		userServiceResponse.setId(id);
		userService.updateUser(userServiceResponse);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteUser(@PathVariable("id")Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
