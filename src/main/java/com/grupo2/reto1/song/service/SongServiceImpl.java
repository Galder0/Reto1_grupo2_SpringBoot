package com.grupo2.reto1.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.reto1.song.repository.SongRepository;

@Service
public class SongServiceImpl implements SongService{
	
	@Autowired
	SongRepository songRepository;

}
