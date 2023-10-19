package com.grupo2.reto1.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.reto1.song.model.SongServiceResponse;
import com.grupo2.reto1.song.repository.SongRepository;
import com.grupo2.reto1.song.service.SongService;
import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserServiceResponse;
import com.grupo2.reto1.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SongService songService;

	@Override
	public List<UserServiceResponse> getAllUsers() {
    
		List<UserServiceResponse> response = new ArrayList<>();
		List<User> UserList = userRepository.getAllUsers();
		for (User user : UserList) {
			response.add(new UserServiceResponse(
					user.getId(),
					user.getName(),
					user.getSurname(),
					user.getEmail(),
					user.getPassword()));
		}
		return response;
	}

	@Override
	public UserServiceResponse getUserById(Integer id) {
		UserServiceResponse response = new UserServiceResponse();
		User user = userRepository.getUserById(id);
		response = (new UserServiceResponse(
				user.getId(),
				user.getName(),
				user.getSurname(),
				user.getEmail(),
				user.getPassword()));
		return response;
	}

	@Override
	public int createUser(UserServiceResponse userServiceResponse) {
		User response = new User(
				userServiceResponse.getName(),
				userServiceResponse.getSurname(),
				userServiceResponse.getEmail(),
				userServiceResponse.getPassword());
		
		response.setId((int) 1L);
		return userRepository.createUser(response);
	}

	@Override
	public int updateUser(UserServiceResponse userServiceResponse) {
		User response = new User(
				userServiceResponse.getId(),
				userServiceResponse.getName(),
				userServiceResponse.getSurname(),
				userServiceResponse.getEmail(),
				userServiceResponse.getPassword());
		return userRepository.updateUser(response);
	}

	@Override
	public int deleteUser(Integer id) {
		return userRepository.deleteUser(id);
	}

	//Get the favorite songs of the user
	@Override
	public UserServiceResponse getUserWithItsFavourites(Integer id) {
		UserServiceResponse response = new UserServiceResponse();
		User user = userRepository.getUserById(id);
		response = (new UserServiceResponse(
				user.getId(),
				user.getName(),
				user.getSurname(),
				user.getEmail(),
				user.getPassword()));
		response.setFavourites(songService.getAllFavouritesFromUser(id));
		return response;
	}
	
	//Delete from favorites
	@Override
	public int deleteFavouriteFromUser(Integer id, Integer userId) {
		return songService.deleteFavouriteSong(id, userId);
	}
	
	//Create favorites
	@Override
	public int createFavouriteSongFromUser(Integer idSong, Integer id) {
		return songService.createFavouriteSongFromUser(idSong, id);
	}
}
