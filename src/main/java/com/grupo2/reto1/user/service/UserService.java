package com.grupo2.reto1.user.service;

import java.util.List;


import com.grupo2.reto1.exceptions.SongNotFoundException;
import com.grupo2.reto1.exceptions.UserNotFoundException;
import com.grupo2.reto1.song.model.SongServiceResponse;
import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserLoginPostRequest;

import com.grupo2.reto1.user.model.UserPostRequest;
import com.grupo2.reto1.user.model.UserServiceResponse;

public interface UserService {

	List<UserServiceResponse> getAllUsers();
  
	UserServiceResponse getUserById(Integer id) throws UserNotFoundException;

	int createUser(UserServiceResponse userServiceResponse);

	int updateUser(UserServiceResponse userServiceResponse)throws UserNotFoundException;

	int deleteUser(Integer id) throws UserNotFoundException;

	UserServiceResponse getUserWithItsFavourites(Integer id)throws UserNotFoundException;

	int deleteFavouriteFromUser(Integer id, Integer userId);

	int createFavouriteSongFromUser(Integer idSong, Integer id);

	Integer logUser(UserLoginPostRequest user);

	List<SongServiceResponse> getAllFavourites(Integer id);

}
