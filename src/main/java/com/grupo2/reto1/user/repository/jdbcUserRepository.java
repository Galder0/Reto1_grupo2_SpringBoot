package com.grupo2.reto1.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.grupo2.reto1.exceptions.SongNotFoundException;
import com.grupo2.reto1.exceptions.UserNotFoundException;
import com.grupo2.reto1.user.model.User;
import com.grupo2.reto1.user.model.UserServiceResponse;

@Repository
public class jdbcUserRepository implements UserRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getAllUsers() {
		return jdbcTemplate.query("Select * from users_table", BeanPropertyRowMapper.newInstance(User.class));
	}

	@Override
	public User getUserById(Integer id) throws UserNotFoundException {
		try {
			return jdbcTemplate.queryForObject("Select * from users_table where id = ?", BeanPropertyRowMapper.newInstance(User.class), id);
		} catch(Exception e){
				throw new UserNotFoundException("User with the id " + id + " not found");
		}
	}

//	@Override
//	public int createUser(User user) {
//		return jdbcTemplate.update("Insert into users_table (name, surname, email, password) VALUES(?,?,?,?)",
//				new Object[] {user.getName(), user.getSurname(), user.getEmail(), user.getPassword()});
//	}
	
	@Override
	public int createUser(User user) {
		// IMPORTANTE: la contrasenia ha tenido que ser cifrada antes de entrar aqui
		
		// TODO podria darnos excepcion por que el email es unico		
		return jdbcTemplate.update("INSERT INTO users_table (name, surname,email, password) VALUES (?, ?, ?, ?)",
			new Object[] { 
				user.getName(),
				user.getSurname(),
				user.getEmail(), 
				user.getPassword() // debe estar cifrada de antemano
			}	
		);
	}

	@Override
	public int updateUser(User user) throws UserNotFoundException {
		try {
			return jdbcTemplate.update("Update users_table set name = ?, surname = ?, email = ?, password = ? where id = ?",
					new Object[] {user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getId()});
		} catch(Exception e){
			throw new UserNotFoundException("User to update not found");
		}
	}

	@Override
	public int deleteUser(Integer id) throws UserNotFoundException {
		try {
			return jdbcTemplate.update("Delete from users_table where id = ?", id);
		} catch(Exception e){
			throw new UserNotFoundException("User to delete with id " + id + " not found");
		}
	}
	
	@Override
	public Optional<UserServiceResponse> findByEmail(String email) {
		// veremos el optional mas adelante
		try {
			UserServiceResponse user = jdbcTemplate.queryForObject("SELECT * from users_table where email = ?", BeanPropertyRowMapper.newInstance(UserServiceResponse.class), email);
			return Optional.of(user);
		} catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public int updatePassword(int id, String newPassword) {
		
		return jdbcTemplate.update("Update users_table set password = ? where id = ?",newPassword, id);
	}

	@Override
	public Integer updateMail(int id, String newMail) {
		
		return jdbcTemplate.update("Update users_table set email = ? where id = ?",newMail, id);
	}

}
