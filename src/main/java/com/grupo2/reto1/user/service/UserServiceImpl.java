package com.grupo2.reto1.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.reto1.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

}
