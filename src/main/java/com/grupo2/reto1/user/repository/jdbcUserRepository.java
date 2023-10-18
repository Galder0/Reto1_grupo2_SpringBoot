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
	public List<User> findAll() {
		return jdbcTemplate.query("SELECT * FROM users_table;", BeanPropertyRowMapper.newInstance(User.class));
	}

	@Override
	public User findById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * FROM users_table where ID = ?",BeanPropertyRowMapper.newInstance(User.class), id);
	}

	@Override
	public int create(User user) {
		return jdbcTemplate.update(
				"INSERT INTO users_table (id, name, surname, email, password) VALUES(?, ?, ?, ?, ?)",
				new Object[] { 
						user.getId(),
						user.getName(),
						user.getSurname(),
						user.getEmail(),
						user.getPassword() 
		});
	}

	@Override
	public int update(User user) {
		return jdbcTemplate.update(
				"UPDATE users_table SET name = ?, surname = ?, email = ?, password = ? WHERE id = ?",
				new Object[] { 
						user.getId(),
						user.getName(),
						user.getSurname(),
						user.getEmail(),
						user.getPassword() 
		});
	}

	@Override
	public int deleteById(Integer id) {
		return jdbcTemplate.update("DELETE FROM users_table WHERE id = ?", id);
	}

}
