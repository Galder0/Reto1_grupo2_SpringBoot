package com.grupo2.reto1.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.reto1.song.service.SongService;

@RestController
public class SongController {
	
	@Autowired
	SongService songService;

}
