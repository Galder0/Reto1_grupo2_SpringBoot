package com.grupo2.reto1.song.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.reto1.exceptions.SongNotFoundException;
import com.grupo2.reto1.song.model.Song;
import com.grupo2.reto1.song.model.SongServiceResponse;
import com.grupo2.reto1.song.repository.SongRepository;

@Service
public class SongServiceImpl implements SongService{
	
	@Autowired
	SongRepository songRepository;

	@Override
	public List<SongServiceResponse> getAllSongs() {
		List<SongServiceResponse> response = new ArrayList<>();
		List<Song> songList = songRepository.getAllSongs();
		for (Song song : songList) {
			response.add(new SongServiceResponse(
					song.getId(),
					song.getTitle(),
					song.getAuthor(),
					song.getUrl(),
					song.getViews()));
		}
		return response;
	}

	@Override
	public SongServiceResponse getSongById(Integer id) throws SongNotFoundException {
		SongServiceResponse response = new SongServiceResponse();
		Song song = songRepository.getSongById(id);
		response = (new SongServiceResponse(
				song.getId(),
				song.getTitle(),
				song.getAuthor(),
				song.getUrl()));
		return response;
	}

	@Override
	public int createSong(SongServiceResponse songServiceResponse) {
		Song response = new Song(
				songServiceResponse.getTitle(),
				songServiceResponse.getAuthor(),
				songServiceResponse.getUrl());
		response.setId((int) 1L);
		return songRepository.createSong(response);
		}

	@Override
	public int updateSong(SongServiceResponse songServiceResponse)throws SongNotFoundException  {
		if (getSongById(songServiceResponse.getId()) != null) {
			Song response = new Song(
					songServiceResponse.getId(),
					songServiceResponse.getTitle(),
					songServiceResponse.getAuthor(),
					songServiceResponse.getUrl());
			return songRepository.updateSong(response);
		}else {
			return (Integer) null;
		}
	}

	@Override
	public int deleteSong(Integer id) throws SongNotFoundException {
		if (getSongById(id) != null) {
			return songRepository.deleteSong(id); 
		}else {
			return (Integer) null;
		}
//		getSongById(id);
//		return songRepository.deleteSong(id);
	}

	//Get the favorites from user
	@Override
	public List<SongServiceResponse> getAllFavouritesFromUser(Integer id) {
		List<SongServiceResponse> response = new ArrayList<>();
		List<Song> songList = songRepository.getAllFavouritesFromUser(id);
		for (Song song : songList) {
			response.add(new SongServiceResponse(
					song.getId(),
					song.getTitle(),
					song.getAuthor(),
					song.getUrl()));
		}
		return response;
	}
	
	//Delete a song from favorites given an user
	@Override
	public int deleteFavouriteSong(Integer id, Integer idSong) {
		return songRepository.deleteFavouriteSong(id, idSong);
	}
	
	//Create a song in favorites given the songs id
	@Override
	public int createFavouriteSongFromUser(Integer idSong, Integer id) {
		return songRepository.createFavouriteSongFromUser(idSong, id);
	}

	@Override
	public List<SongServiceResponse> getAllFavourites(Integer id) {
		List<SongServiceResponse> response = new ArrayList<>();
		List<Song> songList = songRepository.getAllFavouritesFromUser(id);
		for (Song song : songList) {
			response.add(new SongServiceResponse(
					song.getId(),
					song.getTitle(),
					song.getAuthor(),
					song.getUrl(),
					song.getViews()));
		}
		return response;
	}

	@Override
	public int sumViewToSong(Integer id) {
		
		return songRepository.sumViewToSong(id);
	}
	
}
