package com.grupo2.reto1.user.service;

import java.util.List;

import com.grupo2.reto1.song.model.SongServiceResponse;
import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserPostRequest;
import com.grupo2.reto1.user.model.UserServiceResponse;

public interface UserService {

	List<UserServiceResponse> getAllUsers();
  
	UserServiceResponse getUserById(Integer id);

	int createUser(UserServiceResponse userServiceResponse);

	int updateUser(UserServiceResponse userServiceResponse);

	int deleteUser(Integer id);

	UserServiceResponse getUserWithItsFavourites(Integer id);

	int deleteFavouriteFromUser(Integer id, Integer userId);

	int createFavouriteSongFromUser(Integer idSong, Integer id);

  Integer logUser(UserPostRequest user);
}
