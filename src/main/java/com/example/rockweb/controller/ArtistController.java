package com.example.rockweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rockweb.model.Artist;
import com.example.rockweb.repository.ArtistRepository;
import com.example.rockweb.repository.SongRepository;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
public class ArtistController {
	
    @Autowired
    private ArtistRepository artistRepository;
    
    @Autowired
    private SongRepository songRepository;
	
    @PutMapping("/artist/add")
    public Artist createArtist(@RequestBody ArtistDto artistDto) {
    	if(artistDto.getName().isBlank() || artistDto.getExternalId() == null) {
    		return null;
    	}
    	
    	if(artistRepository.findByName(artistDto.getName()) != null) {
    		System.out.println("found duplicate artist, returning...");
    		return null;
    	}
    	
        return artistRepository.save(doCreateArtist(artistDto));
    }
    
    @PostMapping("/artist/add/multiple")
    public Iterable<Artist> createArtists(@RequestBody List<ArtistDto> artistDtos) {
    	List<Artist> artists = new ArrayList<>();
    	
    	boolean hasArtists = artistRepository.count() > 0;
    	for(ArtistDto artistDto : artistDtos) {
    		if(containsName(artists, artistDto.getName()) || (hasArtists && artistRepository.findByName(artistDto.getName()) != null)) {
    			System.out.println("found duplicate artist, skipping...");
    			continue;
    		}
        	
        	artists.add(doCreateArtist(artistDto));
    	}
    	
        return artistRepository.saveAll(artists);
    }
    
    @DeleteMapping("/artist/delete/{artistId}")
    public boolean deleteArtist(@PathVariable Long artistId) {
    	Optional<Artist> artist = artistRepository.findById(artistId);
    	
    	if(artist.isPresent()) {
        	songRepository.deleteAllWithArtist(artist.get());
        	artistRepository.delete(artist.get());
        	System.out.println("Deleted artist with id: [" + artistId + "] and all corresponding songs");
        	return true;
    	}else {
    		System.out.print("artist not found in db, returning...");
    		return false;
    	}
    }
    
    private Artist doCreateArtist(ArtistDto artistDto) {
    	Artist artist = new Artist();
    	artist.setName(artistDto.getName());
    	artist.setId(artistDto.getExternalId());
    	
    	return artist;
    }
    
    private boolean containsName(final List<Artist> list, final String artistName){
        return list.stream().anyMatch(o -> o.getName().equals(artistName));
    }
    
    private static class ArtistDto {
    	@JsonProperty("Id")
    	Long externalId;
    	@JsonProperty("Name")
    	String name;
    	
    	public ArtistDto() {}

		public Long getExternalId() {
			return externalId;
		}
		
		public void setExternalId(Long externalId) {
			this.externalId = externalId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
    }
}
