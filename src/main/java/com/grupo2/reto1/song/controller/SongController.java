package com.grupo2.reto1.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.grupo2.reto1.exceptions.SongNotFoundException;
import com.grupo2.reto1.song.model.SongPostRequest;
import com.grupo2.reto1.song.model.SongServiceResponse;
import com.grupo2.reto1.song.service.SongService;

import jakarta.validation.Valid;

@RequestMapping("/api/songs")
@RestController
public class SongController {
	
	@Autowired
	SongService songService;
	
	@GetMapping
	public ResponseEntity<List<SongServiceResponse>> getAllSongs(){
		return new ResponseEntity<>(songService.getAllSongs(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SongServiceResponse> getSongById(@PathVariable("id") Integer id) {
	    try {
	        SongServiceResponse song = songService.getSongById(id);
	        return new ResponseEntity<>(song, HttpStatus.OK);
	    } catch (SongNotFoundException e) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
	    }
	}

	
	@PostMapping
	public ResponseEntity<Integer> createSong(@Valid @RequestBody SongPostRequest songPostRequest){
		SongServiceResponse songServiceResponse = new SongServiceResponse(songPostRequest.getTitle(), songPostRequest.getAuthor(), songPostRequest.getUrl());
		return new ResponseEntity<> (songService.createSong(songServiceResponse), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> updateSong(@PathVariable("id") Integer id, @RequestBody SongPostRequest songPostRequest){
		SongServiceResponse songServiceReponse = new SongServiceResponse(songPostRequest.getTitle(), songPostRequest.getAuthor(), songPostRequest.getUrl());
		try {
			songServiceReponse.setId(id);
			songService.updateSong(songServiceReponse);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SongNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteSong(@PathVariable("id")Integer id){
		try {
			songService.deleteSong(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SongNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	//Lista de Favoritos SOLO canciones
	@GetMapping("/{id}/favourites")
	public ResponseEntity<List<SongServiceResponse>> getAllFavourites(@PathVariable("id") Integer id){
		return new ResponseEntity<>(songService.getAllFavourites(id), HttpStatus.ACCEPTED);
	}

}
