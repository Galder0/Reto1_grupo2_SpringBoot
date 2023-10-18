package com.grupo2.reto1.song.service;

import java.util.List;

import com.grupo2.reto1.song.model.SongServiceResponse;

public interface SongService {

	List<SongServiceResponse> getAllSongs();

	SongServiceResponse getSonById(Integer id);

	int createSong(SongServiceResponse songServiceResponse);

	int updateSong(SongServiceResponse songServiceReponse);

	int deleteSong(Integer id);

}
