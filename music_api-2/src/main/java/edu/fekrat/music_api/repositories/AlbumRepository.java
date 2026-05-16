package edu.fekrat.music_api.repositories;

import edu.fekrat.music_api.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByTitleContaining(String title);
    List<Album> findByArtistName(String artistName);
    List<Album> findByReleaseYear(Integer releaseYear);
    List<Album> findByReleaseYearBetween(Integer start, Integer end);
    List<Album> findByNumTracks(Integer numTracks);
    List<Album> findByNumTracksGreaterThan(Integer numTracks);
    List<Album> findByNumTracksLessThan(Integer numTracks);

}
