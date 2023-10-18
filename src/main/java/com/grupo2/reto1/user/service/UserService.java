package com.grupo2.reto1.user.service;

import java.util.List;

import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserServiceResponse;

public interface UserService {

	List<UserServiceResponse> getAllUsers();

	UserServiceResponse getUserById(Integer id);

	int createUser(UserServiceResponse userServiceResponse);

	int updateUser(UserServiceResponse userServiceResponse);

	int deleteUser(Integer id);

}
