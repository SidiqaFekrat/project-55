package edu.fekrat.music_api.services;
import edu.fekrat.music_api.entities.Album;
import edu.fekrat.music_api.entities.Artist;
import edu.fekrat.music_api.exceptions.AlreadyExistsException;
import edu.fekrat.music_api.exceptions.NotFoundException;
import edu.fekrat.music_api.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;

    }

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        if (artists.isEmpty()) {
            throw new NotFoundException("no artists found in the database.");
        }
        return artists;
    }



    @Override
    public List<Artist> getArtistsByName(String name) {
        List<Artist> artists = artistRepository.findByNameContaining(name);
        if (artists.isEmpty()) {
            throw new NotFoundException("no artists found with name: " + name);
        }
        return artists;
    }

    @Override
    public List<Artist> getArtistsByGenre(String genre) {
        List<Artist> artists = artistRepository.findByGenreContaining(genre);
        if (artists.isEmpty()) {
            throw new NotFoundException("no artists found with genre: " + genre);
        }
        return artists;
    }

    @Override
    public List<Artist> getArtistsByOriginLocation(String originLocation) {
        List<Artist> artists = artistRepository.findByOriginLocationContaining(originLocation);
        if (artists.isEmpty()) {
            throw new NotFoundException("no artists found with origin location: " + originLocation);
        }
        return artists;
    }


    @Override
    public List<Album> getAlbumsByArtistName(String artistName) {
        // first it check the artist exists
        List<Artist> artists = artistRepository.findByName(artistName);
        if (artists.isEmpty()) {
            throw new NotFoundException("no artist found with name: " + artistName);
        }
        // then get albums from the first exact match
        List<Album> albums = artists.get(0).getAlbums();
        if (albums.isEmpty()) {
            throw new NotFoundException("no albums found for artist: " + artistName);
        }
        return albums;
    }

    @Override
    public Artist addArtist(Artist artist) {
        // Check for duplicate name (exact match)
        List<Artist> existing = artistRepository.findByName(artist.getName());
        if (!existing.isEmpty()) {
            throw new AlreadyExistsException("artist already exists with name: " + artist.getName());
        }
        return artistRepository.save(artist);
    }

    @Override
    public void deleteArtistByName(String artistName) {
        List<Artist> artists = artistRepository.findByName(artistName);
        if (artists.isEmpty()) {
            throw new NotFoundException("No artist found with name: " + artistName);
        }
        artistRepository.delete(artists.get(0));
    }


}





