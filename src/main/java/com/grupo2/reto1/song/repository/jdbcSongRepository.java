package com.grupo2.reto1.song.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.grupo2.reto1.exceptions.SongNotFoundException;
import com.grupo2.reto1.song.model.Song;

@Repository
public class jdbcSongRepository implements SongRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Song> getAllSongs() {
		return jdbcTemplate.query("Select * from songs", BeanPropertyRowMapper.newInstance(Song.class));
	}

	@Override
	public Song getSongById(Integer id) throws SongNotFoundException{
		try {
			return jdbcTemplate.queryForObject("Select * from songs where id = ?", BeanPropertyRowMapper.newInstance(Song.class), id);
		} catch(Exception e){
			throw new SongNotFoundException("Song with the id " + id + " not found");
		}
	}

	@Override
	public int createSong(Song response) {
		return jdbcTemplate.update("Insert into songs (title, author, URL) VALUES(?,?,?)",
				new Object[] {response.getTitle(), response.getAuthor(), response.getUrl()});
	}

	@Override
	public int updateSong(Song response) throws SongNotFoundException{
		try {
		return jdbcTemplate.update("Update songs set title = ?, author = ?, URL = ? where id = ?",
				new Object[] {response.getTitle(), response.getAuthor(), response.getUrl(), response.getId()});
		} catch(Exception e){
			throw new SongNotFoundException("Song to Update not found");
		}
	}

	@Override
	public int deleteSong(Integer id) throws SongNotFoundException{
		try {
		return jdbcTemplate.update("Delete from songs where id = ?", id);
		} catch(Exception e){
			throw new SongNotFoundException("Song with the id " + id + " not found");
		}
	}

	//Get the favorites from user
	@Override
	public List<Song> getAllFavouritesFromUser(Integer id) {
		return jdbcTemplate.query("select * from songs join favourite_songs on songs.id = favourite_songs.song_id where user_id = ?", BeanPropertyRowMapper.newInstance(Song.class), id);
	}

	//Delete a favorite song given a user
	@Override
	public int deleteFavouriteSong(Integer id, Integer userId) {
		return jdbcTemplate.update("Delete from favourite_songs where song_id = ? and user_id = ?", id, userId);
	}

	//Create a favorite song given a user
	@Override
	public int createFavouriteSongFromUser(Integer idSong, Integer userId) {
		return jdbcTemplate.update("INSERT into favourite_songs (song_id, user_id) values(?, ?)", idSong, userId);
	}

	@Override
	public int sumViewToSong(Integer id) {
		return jdbcTemplate.update("Update songs set views = views + 1 where id = ?", id);
	}

}
