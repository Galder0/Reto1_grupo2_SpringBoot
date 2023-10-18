package com.grupo2.reto1.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcUserRepository implements UserRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

}
