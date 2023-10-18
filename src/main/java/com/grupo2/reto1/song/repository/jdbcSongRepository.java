package com.grupo2.reto1.song.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcSongRepository implements SongRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

}
