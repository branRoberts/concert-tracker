package com.pluralsight.concerttracker.data;

import com.pluralsight.concerttracker.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Integer> {

    @Query("SELECT c FROM Concert c WHERE c.year = :year")
    List<Concert> findByYear(@Param("year") int year);

    @Query("SELECT c FROM Concert c WHERE LOWER(c.artist.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Concert> findByArtistName(@Param("name") String name);

    @Query("SELECT c FROM Concert c WHERE LOWER(c.venue.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Concert> findByVenueName(@Param("name") String name);

    @Query("SELECT c FROM Concert c WHERE LOWER(c.venue.city) = LOWER(:city)")
    List<Concert> findByCity(@Param("city") String city);

    @Query("SELECT c FROM Concert c WHERE c.ticketPrice <= :maxPrice")
    List<Concert> findByMaxPrice(@Param("maxPrice") double maxPrice);

    @Query("SELECT c FROM Concert c WHERE c.ticketPrice >= :minPrice AND c.ticketPrice <= :maxPrice")
    List<Concert> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query("SELECT c FROM Concert c WHERE c.ticketPrice <= :maxPrice AND c.year >= :earliestYear")
    List<Concert> findByMaxPriceAndEarliestYear(@Param("maxPrice") double maxPrice, @Param("earliestYear") int earliestYear);
}