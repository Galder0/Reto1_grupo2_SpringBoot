package com.grupo2.reto1.user.repository;

import java.util.List;

import com.grupo2.reto1.user.model.User;

public interface UserRepository {
	
	List<User> findAll();
	User findById(Integer id);
	int create(User user);
	int update(User user);
	int deleteById(Integer id);
}
