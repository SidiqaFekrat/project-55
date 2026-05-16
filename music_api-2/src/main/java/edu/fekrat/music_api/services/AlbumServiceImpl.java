package edu.fekrat.music_api.services;

import edu.fekrat.music_api.entities.Album;
import edu.fekrat.music_api.entities.Artist;
import edu.fekrat.music_api.exceptions.AlreadyExistsException;
import edu.fekrat.music_api.exceptions.NotFoundException;
import edu.fekrat.music_api.repositories.AlbumRepository;
import edu.fekrat.music_api.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        if (albums.isEmpty()) {
            throw new NotFoundException("There are no albums found in the database.");
        }
        return albums;
    }

    @Override
    public List<Album> getAlbumsByTitle(String title) {
        List<Album> albums = albumRepository.findByTitleContaining(title);
        if (albums.isEmpty()) {
            throw new NotFoundException("no albums found with title: " + title);
        }
        return albums;
    }

    @Override
    public List<Album> getAlbumsByArtistName(String artistName) {

        //first we check if the artist exist in our dataset
        List<Artist> artists = artistRepository.findByName(artistName);
        if (artists.isEmpty()) {
            throw new NotFoundException("No artist found with name: " + artistName);
        }
        // Then check they have albums
        List<Album> albums = albumRepository.findByArtistName(artistName);
        if (albums.isEmpty()) {
            throw new NotFoundException("no albums found for artist: " + artistName);
        }
        return albums;
    }

    @Override
    public List<Album> getAlbumsByReleaseYear(Integer releaseYear) {
        List<Album> albums = albumRepository.findByReleaseYear(releaseYear);
        if (albums.isEmpty()) {
            throw new NotFoundException("There are no albums found with release year: " + releaseYear);
        }
        return albums;
    }

    @Override
    public List<Album> getAlbumsByReleaseYearBetween(Integer start, Integer end) {
        List<Album> albums = albumRepository.findByReleaseYearBetween(start, end);
        if (albums.isEmpty()) {
            throw new NotFoundException("No albums found between years: " + start + " and " + end);
        }
        return albums;
    }

    @Override
    public List<Album> getAlbumsByNumTracks(Integer numTracks) {
        List<Album> albums = albumRepository.findByNumTracks(numTracks);
        if (albums.isEmpty()) {
            throw new NotFoundException("No albums found with track count: " + numTracks);
        }
        return albums;
    }

    @Override
    public List<Album> getAlbumsByNumTracksGreaterThan(Integer numTracks) {
        List<Album> albums = albumRepository.findByNumTracksGreaterThan(numTracks);
        if (albums.isEmpty()) {
            throw new NotFoundException("No albums found with more than " + numTracks + " tracks.");
        }
        return albums;
    }

    @Override
    public List<Album> getAlbumsByNumTracksLessThan(Integer numTracks) {
        List<Album> albums = albumRepository.findByNumTracksLessThan(numTracks);
        if (albums.isEmpty()) {
            throw new NotFoundException("No albums found with less than " + numTracks + " tracks.");
        }
        return albums;
    }

    @Override
    public Album addAlbumToArtist(String artistName, Album album) {
        // checking if the artist exists
        List<Artist> artists = artistRepository.findByName(artistName);
        if (artists.isEmpty()) {
            throw new NotFoundException("No artist found with name: " + artistName);
        }
        Artist artist = artists.get(0);

        // checking if album doesn't already exist for this artist
        List<Album> existing = albumRepository.findByArtistName(artistName);
        for (Album a : existing) {
            if (a.getTitle().equalsIgnoreCase(album.getTitle())) {
                throw new AlreadyExistsException("Album '" + album.getTitle()
                        + "' already exists for artist: " + artistName);
            }
        }

        // link album to artist and save
        artist.addAlbum(album);
        return albumRepository.save(album);
    }








}
