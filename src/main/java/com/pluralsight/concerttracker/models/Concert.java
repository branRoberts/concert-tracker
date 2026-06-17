package com.pluralsight.concerttracker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "concerts")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int year;
    private double ticketPrice;
    private int ticketsSold;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "promoter_id", nullable = false)
    private Promoter promoter;

    public Concert() {}

    public Concert(int year, double ticketPrice, int ticketsSold, Artist artist, Venue venue, Promoter promoter) {
        this.year = year;
        this.ticketPrice = ticketPrice;
        this.ticketsSold = ticketsSold;
        this.artist = artist;
        this.venue = venue;
        this.promoter = promoter;
    }

    public int getId() { return id; }
    public int getYear() { return year; }
    public double getTicketPrice() { return ticketPrice; }
    public int getTicketsSold() { return ticketsSold; }
    public Artist getArtist() { return artist; }
    public Venue getVenue() { return venue; }
    public Promoter getPromoter() { return promoter; }

    public void setId(int id) { this.id = id; }
    public void setYear(int year) { this.year = year; }
    public void setTicketPrice(double ticketPrice) { this.ticketPrice = ticketPrice; }
    public void setTicketsSold(int ticketsSold) { this.ticketsSold = ticketsSold; }
    public void setArtist(Artist artist) { this.artist = artist; }
    public void setVenue(Venue venue) { this.venue = venue; }
    public void setPromoter(Promoter promoter) { this.promoter = promoter; }

    @Override
    public String toString() {
        return String.format("[%d] %s at %s (%d) — $%.2f — %d tickets sold",
                id, artist.getName(), venue.getName(), year, ticketPrice, ticketsSold);
    }
}