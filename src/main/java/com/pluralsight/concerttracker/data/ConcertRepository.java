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
    @Query("SELECT c.venue.name, SUM(c.ticketPrice * c.ticketsSold) FROM Concert c GROUP BY c.venue.name")
    List<Object[]> revenuePerVenue();

    @Query("SELECT c.venue.name, COUNT(c) FROM Concert c GROUP BY c.venue.name ORDER BY COUNT(c) DESC")
    List<Object[]> concertsPerVenue();

    @Query("SELECT c.artist.name, COUNT(c) FROM Concert c GROUP BY c.artist.name ORDER BY COUNT(c) DESC")
    List<Object[]> concertsPerArtist();

    @Query("SELECT c.year, AVG(c.ticketPrice) FROM Concert c GROUP BY c.year ORDER BY c.year")
    List<Object[]> avgPriceByYear();

    @Query("SELECT c FROM Concert c")
    List<Concert> findAllForCapacityReport();
}