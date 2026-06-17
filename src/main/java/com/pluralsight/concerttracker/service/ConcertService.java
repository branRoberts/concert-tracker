package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.*;
import com.pluralsight.concerttracker.models.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConcertService {

    private final VenueRepository venueRepository;
    private final ArtistRepository artistRepository;
    private final PromoterRepository promoterRepository;
    private final ConcertRepository concertRepository;

    public ConcertService(VenueRepository venueRepository, ArtistRepository artistRepository, PromoterRepository promoterRepository, ConcertRepository concertRepository) {
        this.venueRepository = venueRepository;
        this.artistRepository = artistRepository;
        this.promoterRepository = promoterRepository;
        this.concertRepository = concertRepository;
    }

    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }
    public Optional<Concert> getConcertById(int id) {
        return concertRepository.findById(id);
    }

    public Concert addConcert(int year, double ticketPrice, int ticketsSold, int artistId, int venueId, int promoterId) {
        Artist artist = artistRepository.findById(artistId).orElse(null);
        Venue venue = venueRepository.findById(venueId).orElse(null);
        Promoter promoter = promoterRepository.findById(promoterId).orElse(null);

        if (artist == null || venue == null || promoter == null) {
            System.out.println("Invalid artist, venue, or promoter.");
            return null;
        }
        if (ticketsSold > venue.getCapacity()) {
            System.out.println("Tickets sold exceeds venue capacity of " + venue.getCapacity() + ".");
            return null;
        }
        if (ticketPrice < 0 || ticketsSold < 0) {
            System.out.println("Ticket price and tickets sold cannot be negative.");
            return null;
        }
        return concertRepository.save(new Concert(year, ticketPrice, ticketsSold, artist, venue, promoter));
    }

    public void updateConcertPrice(int id, double price) {
        Concert concert = concertRepository.findById(id).orElse(null);
        if (concert == null) {
            System.out.println("Concert not found.");
            return;
        }
        if (price < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }
        concert.setTicketPrice(price);
        concertRepository.save(concert);
    }

    public void updateConcertTicketsSold(int id, int ticketsSold) {
        Concert concert = concertRepository.findById(id).orElse(null);
        if (concert == null) {
            System.out.println("Concert not found.");
            return;
        }
        if (ticketsSold < 0) {
            System.out.println("Tickets sold cannot be negative.");
            return;
        }
        if (ticketsSold > concert.getVenue().getCapacity()) {
            System.out.println("Tickets sold exceeds venue capacity of " + concert.getVenue().getCapacity() + ".");
            return;
        }
        concert.setTicketsSold(ticketsSold);
        concertRepository.save(concert);
    }

    public void deleteConcert(int id) {
        if (!concertRepository.existsById(id)) {
            System.out.println("Concert not found.");
            return;
        }
        concertRepository.deleteById(id);
    }
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }
    public Venue addVenue(String name, String city, int capacity) {
        return venueRepository.save(new Venue(name, city, capacity));
    }

    public void updateVenueCapacity(int id, int capacity) {
        Venue venue = venueRepository.findById(id).orElse(null);
        if (venue == null) {
            System.out.println("Venue not found.");
            return;
        }
        venue.setCapacity(capacity);
        venueRepository.save(venue);
    }

    public void deleteVenue(int id) {
        if (!venueRepository.existsById(id)) {
            System.out.println("Venue not found.");
            return;
        }
        venueRepository.deleteById(id);
    }

    public List<Venue> findVenuesByCity(String city) {
        return venueRepository.findByCity(city);
    }

    public List<Venue> findVenuesByName(String name) {
        return venueRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Venue> findVenuesByMinCapacity(int capacity) {
        return venueRepository.findByCapacityGreaterThanEqual(capacity);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }
    public Artist addArtist(String name, String genre) {
        return artistRepository.save(new Artist(name, genre));
    }

    public void updateArtistGenre(int id, String genre) {
        Artist artist = artistRepository.findById(id).orElse(null);
        if (artist == null) {
            System.out.println("Artist not found.");
            return;
        }
        artist.setGenre(genre);
        artistRepository.save(artist);
    }

    public void deleteArtist(int id) {
        if (!artistRepository.existsById(id)) {
            System.out.println("Artist not found.");
            return;
        }
        artistRepository.deleteById(id);
    }

    public List<Artist> findArtistsByGenre(String genre) {
        return artistRepository.findByGenreIgnoreCase(genre);
    }

    public List<Artist> findArtistsByName(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Promoter> getAllPromoters() {
        return promoterRepository.findAll();
    }
    public Promoter addPromoter(String name) {
        return promoterRepository.save(new Promoter(name));
    }

    public void deletePromoter(int id) {
        if (!promoterRepository.existsById(id)) {
            System.out.println("Promoter not found.");
            return;
        }
        promoterRepository.deleteById(id);
    }

    public List<Promoter> findPromotersByName(String name) {
        return promoterRepository.findByNameContainingIgnoreCase(name);
    }
    public void loadStartingData(){
        if (concertRepository.count() > 0) return;

        Venue v1 = venueRepository.save(new Venue("Madison Square Garden", "New York", 20000));
        Venue v2 = venueRepository.save(new Venue("The Fillmore", "San Francisco", 1150));
        Venue v3 = venueRepository.save(new Venue("Red Rocks Amphitheatre", "Denver", 9525));

        Artist a1 = artistRepository.save(new Artist("Taylor Swift", "Pop"));
        Artist a2 = artistRepository.save(new Artist("Metallica", "Metal"));
        Artist a3 = artistRepository.save(new Artist("Miles Davis", "Jazz"));

        Promoter p1 = promoterRepository.save(new Promoter("Live Nation"));
        Promoter p2 = promoterRepository.save(new Promoter("AEG Presents"));

        concertRepository.save(new Concert(2022, 150.00, 18000, a1, v1, p1));
        concertRepository.save(new Concert(2023, 200.00, 20000, a1, v3, p1));
        concertRepository.save(new Concert(2021, 75.00, 1100, a2, v2, p2));
        concertRepository.save(new Concert(2022, 95.00, 9000, a2, v3, p2));
        concertRepository.save(new Concert(2023, 50.00, 800, a3, v2, p1));
        concertRepository.save(new Concert(2021, 120.00, 15000, a1, v1, p2));
    }
}