package com.example.rockweb.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Artist")
public class Artist extends BaseEntity {
	private static final long serialVersionUID = -6166910539203738359L;

	@Id
    @GeneratedValue(generator = "artist_generator")
    @Column(name = "artist_id")
    @SequenceGenerator(name = "artist_generator", sequenceName = "artist_sequence")
    private Long id;
    
    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Song> song;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "external_id")
    private long external_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Song> getSong() {
		return song;
	}

	public void setSong(List<Song> song) {
		this.song = song;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getExternal_id() {
		return external_id;
	}

	public void setExternal_id(long external_id) {
		this.external_id = external_id;
	}
}
