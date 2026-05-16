package edu.fekrat.music_api.controllers;

import edu.fekrat.music_api.entities.Album;
import edu.fekrat.music_api.services.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")


public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/all")
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/title/{albumTitle}")
    public List<Album> getAlbumsByTitle(@PathVariable String albumTitle) {
        return albumService.getAlbumsByTitle(albumTitle);
    }

    @GetMapping("/artist/{artistName}")
    public List<Album> getAlbumsByArtistName(@PathVariable String artistName) {
        return albumService.getAlbumsByArtistName(artistName);
    }

    @GetMapping("/releaseYear/{releaseYear}")
    public List<Album> getAlbumsByReleaseYear(@PathVariable Integer releaseYear) {
        return albumService.getAlbumsByReleaseYear(releaseYear);
    }

    @GetMapping("/releaseYearBetween/{startReleaseYear}/{endReleaseYear}")
    public List<Album> getAlbumsByReleaseYearBetween(
            @PathVariable Integer startReleaseYear,
            @PathVariable Integer endReleaseYear) {
        return albumService.getAlbumsByReleaseYearBetween(startReleaseYear, endReleaseYear);
    }

    @GetMapping("/numTracks/{numTracks}")
    public List<Album> getAlbumsByNumTracks(@PathVariable Integer numTracks) {
        return albumService.getAlbumsByNumTracks(numTracks);
    }

    @GetMapping("/numTracksGreaterThan/{numTracks}")
    public List<Album> getAlbumsByNumTracksGreaterThan(@PathVariable Integer numTracks) {
        return albumService.getAlbumsByNumTracksGreaterThan(numTracks);
    }

    @GetMapping("/numTracksLessThan/{numTracks}")
    public List<Album> getAlbumsByNumTracksLessThan(@PathVariable Integer numTracks) {
        return albumService.getAlbumsByNumTracksLessThan(numTracks);
    }


    @PostMapping("/artist/{artistName}")
    public ResponseEntity<Album> addAlbumToArtist(
            @PathVariable String artistName,
            @RequestBody Album album) {
        return new ResponseEntity<>(albumService.addAlbumToArtist(artistName, album), HttpStatus.CREATED);
    }




}
