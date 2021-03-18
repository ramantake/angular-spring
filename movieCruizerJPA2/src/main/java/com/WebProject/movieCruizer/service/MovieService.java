package com.WebProject.movieCruizer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WebProject.movieCruizer.model.Movie;
import com.WebProject.movieCruizer.repository.MovieRepository;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepository;
	
	public List<Movie> getAllMovies(){
		return movieRepository.findAll();
		
	}
	
	@Transactional
	public void editMovie(Movie movie) {
		movieRepository.save(movie);
	}
	
	
}
