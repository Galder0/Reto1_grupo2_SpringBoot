package com.grupo2.reto1.song.repository;

import java.util.List;

import com.grupo2.reto1.exceptions.SongNotFoundException;
import com.grupo2.reto1.song.model.Song;

public interface SongRepository {

	List<Song> getAllSongs();

	Song getSongById(Integer id) throws SongNotFoundException;

	int createSong(Song response);

	int updateSong(Song response)throws SongNotFoundException;

	int deleteSong(Integer id) throws SongNotFoundException;
	
	List<Song> getAllFavouritesFromUser(Integer id);

	int deleteFavouriteSong(Integer id, Integer userId);

	int createFavouriteSongFromUser(Integer idSong, Integer id);

}
