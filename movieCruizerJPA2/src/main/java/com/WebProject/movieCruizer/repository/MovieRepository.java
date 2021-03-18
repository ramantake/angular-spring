package com.WebProject.movieCruizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WebProject.movieCruizer.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

}
