package com.grupo2.reto1.user.repository;

import java.util.List;

import com.grupo2.reto1.user.model.User;

public interface UserRepository {

	List<User> getAllUsers();

	User getUserById(Integer id);

	int createUser(User user);

	int updateUser(User user);

	int deleteUser(Integer id);

	User getLogedUserInfo(String userEmail);

}
