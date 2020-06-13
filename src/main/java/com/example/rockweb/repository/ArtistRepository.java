package com.example.rockweb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.rockweb.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
	@Query("SELECT a FROM Artist a WHERE a.name = ?1")
	Artist findByName(String name);
}
