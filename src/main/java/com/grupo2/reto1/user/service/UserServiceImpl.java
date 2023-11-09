package com.grupo2.reto1.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grupo2.reto1.exceptions.UserNotFoundException;
import com.grupo2.reto1.song.model.SongServiceResponse;
import com.grupo2.reto1.song.service.SongService;
import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserLoginPostRequest;
import com.grupo2.reto1.user.model.UserPostRequest;
import com.grupo2.reto1.user.model.UserServiceResponse;
import com.grupo2.reto1.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

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
	public UserServiceResponse getUserById(Integer id) throws UserNotFoundException{
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
	public int updateUser(UserServiceResponse userServiceResponse) throws UserNotFoundException {
		User response = new User(
				userServiceResponse.getId(),
				userServiceResponse.getName(),
				userServiceResponse.getSurname(),
				userServiceResponse.getEmail(),
				userServiceResponse.getPassword());
		return userRepository.updateUser(response);
	}

	@Override
	public int deleteUser(Integer id) throws UserNotFoundException  {
		return userRepository.deleteUser(id);
	}

	@Override
	public Integer logUser(UserLoginPostRequest userDTO) {
		Integer response = 0;
		List<User> userlist = userRepository.getAllUsers();
		for (User user : userlist) {
			if (user.getEmail().equals(userDTO.getEmail()) && user.getPassword().equals(userDTO.getPassword())) {
				response = user.getId();
			}
		}
		return response;
	}
	//Get the favorite songs of the user
	@Override
	public UserServiceResponse getUserWithItsFavourites(Integer id) {
		UserServiceResponse response = new UserServiceResponse();
		User user;
		try {
			user = userRepository.getUserById(id);
			response = (new UserServiceResponse(
					user.getId(),
					user.getName(),
					user.getSurname(),
					user.getEmail(),
					user.getPassword()));
			response.setFavourites(songService.getAllFavouritesFromUser(id));
			return response;
		} catch (UserNotFoundException e) {
			return null;
		}
	}

	//Delete from favorites
	@Override
	public int deleteFavouriteFromUser(Integer idSong, Integer userId) {
		return songService.deleteFavouriteSong(idSong, userId);
	}

	//Create favorites
	@Override
	public int createFavouriteSongFromUser(Integer idSong, Integer userId) {
		return songService.createFavouriteSongFromUser(idSong, userId);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	// esta es la funcion que busca al usuario por email. 
		// ya que en este caso el campo de login es el email
    	// si fuese otro, realizar otra funcion
        return userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " not found"));
	}

	@Override
	public List<SongServiceResponse> getAllFavourites(Integer id) {
		
		return songService.getAllFavourites(id);
	}

}

