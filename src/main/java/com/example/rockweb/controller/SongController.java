package com.example.rockweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rockweb.model.Artist;
import com.example.rockweb.model.Song;
import com.example.rockweb.repository.ArtistRepository;
import com.example.rockweb.repository.SongRepository;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
public class SongController {
	
    @Autowired
    private SongRepository songRepository;
    
    @Autowired
    private ArtistRepository artistRepository;
    
    @GetMapping("/song/artist/{artistId}")
    public Iterable<Song> findSongsByArtist(@PathVariable Long artistId){
    	Optional<Artist> artist = artistRepository.findById(artistId);
    	
    	if(artist.isPresent()) {
    		return songRepository.findByArtist(artist.get());
    	}
    	
		return null;
    }
    
    @GetMapping("/song/genre/{genreName}")
    public Iterable<Song> findSongsByGenre(@PathVariable String genreName){
    	if(genreName.isBlank()) {
    		return null;
    	}
    	
    	return songRepository.findByGenre(genreName);
    }
    
    @GetMapping("/song/year/{year}")
    public Iterable<Song> findSongsByYear(@PathVariable Integer year){
    	if(year == null) {
    		return null;
    	}
    	
    	return songRepository.findByYear(year);
    }
    
    @PostMapping("/song/add")
    public Song createSong(@RequestBody SongDto songDto){
		Artist artist = artistRepository.findByName(songDto.getArtist());
		
		if(artist == null) {
			System.out.println("artist for song not found");
			return null;
		}
		
		Song song = doCreateSong(songDto, artist);
		return songRepository.save(song);
		
    }
    
    @PostMapping("/song/add/multiple")
    public Iterable<Song> createSongs(@RequestBody List<SongDto> songDtos) {
    	List<Song> songs = new ArrayList<>();
    	
    	for(SongDto songDto : songDtos) {
    		Artist artist = artistRepository.findByName(songDto.getArtist());
    		
    		if(artist == null) {
    			System.out.println("artist for song not found, skipping...");
    			continue;
    		}
    		
    		songs.add(doCreateSong(songDto, artist));
    	}
        return songRepository.saveAll(songs);
    }
    
    @DeleteMapping("/song/delete/{songId}")
    public boolean deleteSong(@PathVariable Long songId) {
    	Optional<Song> song = songRepository.findById(songId);
    	
    	if(song.isPresent()) {
    		songRepository.deleteById(songId);
    		return true;
    	}else {
    		System.out.print("song not found in db, returning...");
    		return false;
    	}
    }
    
    private Song doCreateSong(SongDto songDto, Artist artist) {
    	Song song = new Song();
		song.setArtist(artist);
		
		song.setExternalId(songDto.getId());
		song.setName(songDto.getName());
		song.setYear(songDto.getYear());
		song.setShortname(songDto.getShortname());
		song.setBpm(songDto.getBpm());
		song.setDuration(songDto.getDuration());
		song.setGenre(songDto.getGenre());
		song.setSpotifyId(songDto.getSpotifyId());
		song.setAlbum(songDto.getAlbum());
		
		return song;
    }
    
    private static class SongDto {
    	@JsonProperty("Id")
    	private long id;
    	
    	@JsonProperty("Name")
    	private String name;
    	
    	@JsonProperty("Year")
    	private int year;
    	
    	@JsonProperty("Artist")
    	private String artist;
    	
    	@JsonProperty("Shortname")
    	private String shortname;
    	
    	@JsonProperty("Bpm")
    	private int bpm;
    	
    	@JsonProperty("Duration")
    	private long duration;
    	
    	@JsonProperty("Genre")
    	private String genre;
    	
    	@JsonProperty("SpotifyId")
    	private String spotifyId;
    	
    	@JsonProperty("Album")
    	private String album;
    	
    	public SongDto() {}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public String getArtist() {
			return artist;
		}

		public void setArtist(String artist) {
			this.artist = artist;
		}

		public String getShortname() {
			return shortname;
		}

		public void setShortname(String shortname) {
			this.shortname = shortname;
		}

		public int getBpm() {
			return bpm;
		}

		public void setBpm(int bpm) {
			this.bpm = bpm;
		}

		public long getDuration() {
			return duration;
		}

		public void setDuration(long duration) {
			this.duration = duration;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public String getSpotifyId() {
			return spotifyId;
		}

		public void setSpotifyId(String spotifyId) {
			this.spotifyId = spotifyId;
		}

		public String getAlbum() {
			return album;
		}

		public void setAlbum(String album) {
			this.album = album;
		}
    }
}
