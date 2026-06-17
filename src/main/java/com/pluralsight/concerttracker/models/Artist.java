package com.pluralsight.concerttracker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String genre;

    public Artist() {}

    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getGenre() { return genre; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", id, name, genre);
    }
}