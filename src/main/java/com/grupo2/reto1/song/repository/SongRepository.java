package com.grupo2.reto1.song.repository;

import java.util.List;

import com.grupo2.reto1.song.model.Song;

public interface SongRepository {

	List<Song> getAllSongs();

	Song getSongById(Integer id);

	int createSong(Song response);

	int updateSong(Song response);

	int deleteSong(Integer id);

}
