package com.example.rockweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Song")
public class Song extends BaseEntity {
	
	private static final long serialVersionUID = 3218328881942269740L;

	@Id
    @GeneratedValue(generator = "song_generator")
    @Column(name = "song_id")
    @SequenceGenerator(name = "song_generator", sequenceName = "song_sequence")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "year")
    private int year;
    
    @Column(name = "shortname")
    private String shortname;
    
    @Column(name = "bpm")
    private int bpm;
    
    @Column(name = "duration")
    private long duration;
    
    @Column(name = "genre")
    private String genre;
    
    @Column(name = "spotify_id")
    private String spotifyId;
    
    @Column(name = "album")
    private String album;
    
    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonIgnore
    private Artist artist;
    
    @Column(name = "external_id")
    private long externalId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public long getExternalId() {
		return externalId;
	}

	public void setExternalId(long externalId) {
		this.externalId = externalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
