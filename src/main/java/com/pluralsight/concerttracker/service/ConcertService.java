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

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public List<Promoter> getAllPromoters() {
        return promoterRepository.findAll();
    }
}