package edu.fekrat.music_api.services;

import edu.fekrat.music_api.entities.Album;
import edu.fekrat.music_api.entities.Artist;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArtistService {

    List<Artist>  getAllArtists();

    List<Artist> getArtistsByName(String name);

    List<Artist>  getArtistsByGenre (String genre);
    List<Artist>  getArtistsByOriginLocation (String originLocation);

    List<Album>  getAlbumsByArtistName(String artistName);
    Artist        addArtist(Artist artist);
    void          deleteArtistByName (String artistName);


}



