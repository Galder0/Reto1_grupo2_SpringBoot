package com.grupo2.reto1.song.service;

import java.util.List;

import com.grupo2.reto1.exceptions.SongNotFoundException;
import com.grupo2.reto1.song.model.SongServiceResponse;

public interface SongService {

	List<SongServiceResponse> getAllSongs();

	SongServiceResponse getSongById(Integer id) throws SongNotFoundException;

	int createSong(SongServiceResponse songServiceResponse);

	int updateSong(SongServiceResponse songServiceReponse) throws SongNotFoundException;

	int deleteSong(Integer id)throws SongNotFoundException;
	
	List<SongServiceResponse> getAllFavouritesFromUser(Integer id);

	int deleteFavouriteSong(Integer idSong, Integer userId);

	int createFavouriteSongFromUser(Integer id, Integer idSong);

	List<SongServiceResponse> getAllFavourites(Integer id);

	int sumViewToSong(Integer id);

}
