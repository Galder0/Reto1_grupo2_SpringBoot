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
		return new ResponseEntity<>(songService.getSonById(id), HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity<Integer> createSong(@Valid @RequestBody SongPostRequest songPostRequest){
		SongServiceResponse songServiceResponse = new SongServiceResponse(songPostRequest.getTitle(), songPostRequest.getAuthor(), songPostRequest.getUrl());
		return new ResponseEntity<> (songService.createSong(songServiceResponse), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> updateSong(@PathVariable("id") Integer id, @RequestBody SongPostRequest songPostRequest){
		SongServiceResponse songServiceReponse = new SongServiceResponse(songPostRequest.getTitle(), songPostRequest.getAuthor(), songPostRequest.getUrl());
		songServiceReponse.setId(id);
		songService.updateSong(songServiceReponse);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteSong(@PathVariable("id")Integer id){
		songService.deleteSong(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
