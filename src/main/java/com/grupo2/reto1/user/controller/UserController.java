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
import org.springframework.web.server.ResponseStatusException;

import com.grupo2.reto1.exceptions.UserNotFoundException;
import com.grupo2.reto1.user.model.UserLoginPostRequest;
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
		try {
			return new ResponseEntity<>(userService.getUserById(id), HttpStatus.ACCEPTED);
		} catch (UserNotFoundException e) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

		}
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
		try {
			userService.updateUser(userServiceResponse);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteUser(@PathVariable("id")Integer id){
		try {
			userService.deleteUser(id);
		} catch (UserNotFoundException e) {
			  throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	
	//GET FAVORITE SONGS
	@GetMapping("/{id}/favourites")
	public ResponseEntity<UserServiceResponse> getUserWithItsFavourites(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(userService.getUserWithItsFavourites(id), HttpStatus.ACCEPTED);
		} catch (UserNotFoundException e) {
			  throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	//DELETE SONG FROM FAVORITE
	@DeleteMapping("/{id}/favourites/{idSong}")
	public ResponseEntity<Integer> deleteFavouriteFromUser(@PathVariable("id")Integer id, @PathVariable("idSong")Integer idSong){
		userService.deleteFavouriteFromUser(idSong, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//CREATE FAVORITE SONG
	@PostMapping("/{userId}/favorites/{songId}")
	public ResponseEntity<Integer> createFavoriteSongForUser(
	        @PathVariable("userId") Integer userId,
	        @PathVariable("songId") Integer songId) {

	    // Call the service method to create a favorite song for the user
	    int favoriteSongId = userService.createFavouriteSongFromUser(userId, songId);

	    // Check if the creation was successful
	    if (favoriteSongId > 0) {
	        return new ResponseEntity<>(favoriteSongId, HttpStatus.CREATED);
	    } else {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	//Login
	@PostMapping("/login")
	public ResponseEntity<Integer> loginUser(@Valid @RequestBody UserLoginPostRequest userPostRequest) {
		UserLoginPostRequest user = new UserLoginPostRequest(userPostRequest.getEmail(), userPostRequest.getPassword());
		return new ResponseEntity<Integer>(userService.logUser(user), HttpStatus.OK);
	}
	
	//Get the user's id when logged
	
}
