package com.pluralsight.concerttracker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "venues")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String city;
    private int capacity;

    public Venue() {}

    public Venue(String name, String city, int capacity) {
        this.name = name;
        this.city = city;
        this.capacity = capacity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public int getCapacity() { return capacity; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    @Override
    public String toString() {
        return String.format("[%d] %s — %s (capacity: %d)", id, name, city, capacity);
    }
}