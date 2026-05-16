package edu.fekrat.music_api.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    private Integer numTracks;

    @ManyToOne
    @JoinColumn (name = "artist_id")
    @JsonIgnore
    private Artist artist;

    public Album(){

    }

    public Album(String title, Integer releaseYear, Integer numTracks){
        this.title = title;
        this.releaseYear = releaseYear;
        this.numTracks = numTracks;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getNumTracks() {
        return numTracks;
    }

    public void setNumTracks(Integer numTracks) {
        this.numTracks = numTracks;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
