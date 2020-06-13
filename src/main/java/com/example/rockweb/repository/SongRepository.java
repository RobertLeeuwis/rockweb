package com.example.rockweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.rockweb.model.Artist;
import com.example.rockweb.model.Song;

public interface SongRepository extends CrudRepository<Song, Long> {
	@Query("SELECT s FROM Song s WHERE s.artist = ?1")
	List<Song> findByArtist(Artist artist);
	
	@Query("SELECT s FROM Song s WHERE lower(s.genre) LIKE lower(concat('%', ?1,'%'))")
	List<Song> findByGenre(String genreName);
	
	@Query("SELECT s FROM Song s WHERE s.year = ?1")
	List<Song> findByYear(Integer releaseYear);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Song s WHERE s.artist = ?1")
	void deleteAllWithArtist(Artist artist);
}
