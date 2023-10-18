package com.grupo2.reto1.user.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.grupo2.reto1.user.model.User;

@Repository
public class jdbcUserRepository implements UserRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getAllUsers() {
		return jdbcTemplate.query("Select * from users_table", BeanPropertyRowMapper.newInstance(User.class));
	}

	@Override
	public User getUserById(Integer id) {
		return jdbcTemplate.queryForObject("Select * from users_table where id = ?", BeanPropertyRowMapper.newInstance(User.class), id);
	}

	@Override
	public int createUser(User user) {
		return jdbcTemplate.update("Insert into users_table (name, surname, email, password) VALUES(?,?,?,?)",
				new Object[] {user.getName(), user.getSurname(), user.getEmail(), user.getPassword()});
	}

	@Override
	public int updateUser(User user) {
		return jdbcTemplate.update("Update users_table set name = ?, surname = ?, email = ?, password = ? where id = ?",
				new Object[] {user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getId()});
	}

	@Override
	public int deleteUser(Integer id) {
		return jdbcTemplate.update("Delete from users_table where id = ?", id);
	}

	
}
