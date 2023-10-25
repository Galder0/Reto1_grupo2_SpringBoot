package com.grupo2.reto1.user.repository;

import java.util.List;

import com.grupo2.reto1.exceptions.UserNotFoundException;
import com.grupo2.reto1.user.model.User;

public interface UserRepository {

	List<User> getAllUsers();

	User getUserById(Integer id) throws UserNotFoundException;

	int createUser(User user);

	int updateUser(User user)throws UserNotFoundException;

	int deleteUser(Integer id)throws UserNotFoundException;

}
