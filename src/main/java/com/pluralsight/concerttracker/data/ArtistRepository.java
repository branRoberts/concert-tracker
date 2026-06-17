package com.pluralsight.concerttracker.data;

import com.pluralsight.concerttracker.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findByGenreIgnoreCase(String genre);
    List<Artist> findByNameContainingIgnoreCase(String name);
}