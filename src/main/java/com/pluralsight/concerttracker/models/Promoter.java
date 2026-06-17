package com.pluralsight.concerttracker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "promoters")
public class Promoter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Promoter() {}

    public Promoter(String name) {
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return String.format("[%d] %s", id, name);
    }
}