package edu.fekrat.music_api.services;


import java.util.List;

import edu.fekrat.music_api.entities.Album;


public interface AlbumService {

    List<Album> getAllAlbums();

    List<Album> getAlbumsByTitle(String title);

    List<Album> getAlbumsByArtistName(String artistName);
    List<Album> getAlbumsByReleaseYear(Integer releaseYear);

    List<Album> getAlbumsByReleaseYearBetween(Integer start, Integer end);
    List<Album> getAlbumsByNumTracks(Integer numTracks);
    List<Album> getAlbumsByNumTracksGreaterThan(Integer numTracks);
    List<Album> getAlbumsByNumTracksLessThan(Integer numTracks);
    Album       addAlbumToArtist(String artistName, Album album);






}
