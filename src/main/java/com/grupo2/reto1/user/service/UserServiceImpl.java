package com.grupo2.reto1.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserServiceResponse;
import com.grupo2.reto1.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserServiceResponse> getAllUsers() {
		List<UserServiceResponse> response = new ArrayList<UserServiceResponse>();
		List<User> userlist = userRepository.findAll();
		for (User user : userlist) {
			response.add(new UserServiceResponse(
					user.getId(), 
					user.getName(), 
					user.getSurname(), 
					user.getEmail(),
					user.getPassword()
					));
		}
		return response;
	}

	@Override
	public UserServiceResponse getUsersById(Integer id) {
		User user = userRepository.findById(id);
		return new UserServiceResponse(
				user.getId(), 
				user.getName(), 
				user.getSurname(), 
				user.getEmail(),
				user.getPassword()
				);
	}

	@Override
	public int createUser(User user) {
		return userRepository.create(user);
	}

	@Override
	public int updateUser(User user) {
		return userRepository.update(user);
	}

	@Override
	public int deleteUserById(Integer id) {
		return userRepository.deleteById(id);
	}

}
