package com.grupo2.reto1.song.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.grupo2.reto1.song.model.Song;
import com.grupo2.reto1.user.model.User;

@Repository
public class jdbcSongRepository implements SongRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Song> getAllSongs() {
		return jdbcTemplate.query("Select * from songs", BeanPropertyRowMapper.newInstance(Song.class));
	}

	@Override
	public Song getSongById(Integer id) {
		return jdbcTemplate.queryForObject("Select * from songs where id = ?", BeanPropertyRowMapper.newInstance(Song.class), id);
	}

	@Override
	public int createSong(Song response) {
		return jdbcTemplate.update("Insert into songs (title, author, URL) VALUES(?,?,?)",
				new Object[] {response.getTitle(), response.getAuthor(), response.getUrl()});
	}

	@Override
	public int updateSong(Song response) {
		return jdbcTemplate.update("Update songs set title = ?, author = ?, URL = ? where id = ?",
				new Object[] {response.getTitle(), response.getAuthor(), response.getUrl(), response.getId()});
	}

	@Override
	public int deleteSong(Integer id) {
		return jdbcTemplate.update("Delete from songs where id = ?", id);
	}

}
