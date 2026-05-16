package edu.fekrat.music_api.repositories;

import edu.fekrat.music_api.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {


    List<Artist> findByNameContaining (String name);
    List<Artist> findByGenreContaining(String genre);
    List<Artist> findByOriginLocationContaining(String originLocation);
    List<Artist> findByName (String name);

}
