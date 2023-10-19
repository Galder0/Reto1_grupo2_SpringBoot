package com.grupo2.reto1.song.repository;

import java.util.List;

import com.grupo2.reto1.song.model.Song;
import com.grupo2.reto1.song.model.SongServiceResponse;

public interface SongRepository {

	List<Song> getAllSongs();

	Song getSongById(Integer id);

	int createSong(Song response);

	int updateSong(Song response);

	int deleteSong(Integer id);
	
	List<Song> getAllFavouritesFromUser(Integer id);

	int deleteFavouriteSong(Integer id, Integer userId);

	int createFavouriteSongFromUser(Integer idSong, Integer id);

}
