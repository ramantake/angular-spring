package com.WebProject.movieCruizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WebProject.movieCruizer.model.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, String> {
	public Long deleteByProductId(int id);
	public List<Favorite> findByUserid(int userid);
}
