package com.grupo2.reto1.user.service;

import java.util.List;

import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserPostRequest;
import com.grupo2.reto1.user.model.UserServiceResponse;

public interface UserService {

	List<UserServiceResponse> getAllUsers();
	UserServiceResponse getUsersById(Integer id);
	int createUser(User user);
	int updateUser(User user);
	int deleteUserById(Integer id);
	Integer logUser(UserPostRequest user);
}
