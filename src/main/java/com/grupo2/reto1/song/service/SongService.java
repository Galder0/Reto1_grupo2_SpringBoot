package com.grupo2.reto1.song.service;

import java.util.List;

import com.grupo2.reto1.song.model.SongServiceResponse;

public interface SongService {

	List<SongServiceResponse> getAllSongs();

	SongServiceResponse getSonById(Integer id);

	int createSong(SongServiceResponse songServiceResponse);

	int updateSong(SongServiceResponse songServiceReponse);

	int deleteSong(Integer id);
	
	List<SongServiceResponse> getAllFavouritesFromUser(Integer id);

	int deleteFavouriteSong(Integer id, Integer userId);

	int createFavouriteSongFromUser(Integer idSong, Integer id);

}
