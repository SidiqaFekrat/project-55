package edu.fekrat.music_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import edu.fekrat.music_api.entities.Album;
import edu.fekrat.music_api.entities.Artist;
import edu.fekrat.music_api.services.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Artist>> getArtistsByName(@PathVariable String name) {
        return ResponseEntity.ok(artistService.getArtistsByName(name));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Artist>> getArtistsByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(artistService.getArtistsByGenre(genre));
    }

    @GetMapping("/originLocation/{originLocation}")
    public ResponseEntity<List<Artist>> getArtistsByOriginLocation(@PathVariable String originLocation) {
        return ResponseEntity.ok(artistService.getArtistsByOriginLocation(originLocation));
    }

    @GetMapping("/{artistName}/albums")
    public ResponseEntity<List<Album>> getAlbumsByArtistName(@PathVariable String artistName) {
        return ResponseEntity.ok(artistService.getAlbumsByArtistName(artistName));
    }

    @DeleteMapping("/delete/{artistName}")
    public ResponseEntity<String> deleteArtistByName(@PathVariable String artistName) {
        artistService.deleteArtistByName(artistName);
        return ResponseEntity.ok("Artist deleted successfully: " + artistName);
    }










}
