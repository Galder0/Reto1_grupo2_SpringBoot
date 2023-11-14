package com.grupo2.reto1.user.repository;

import java.util.List;
import java.util.Optional;

import com.grupo2.reto1.exceptions.UserNotFoundException;
import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserServiceResponse;

public interface UserRepository {

	List<User> getAllUsers();

	User getUserById(Integer id) throws UserNotFoundException;

	int createUser(User user);

	int updateUser(User user)throws UserNotFoundException;

	int deleteUser(Integer id)throws UserNotFoundException;

	Optional<UserServiceResponse> findByEmail(String email);

	int updatePassword(int id, String newPassword);

	Integer updateMail(int id, String newMail);

}
