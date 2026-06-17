package com.pluralsight.concerttracker;

import com.pluralsight.concerttracker.models.*;
import com.pluralsight.concerttracker.service.ConcertService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ConcertTrackerApplication implements CommandLineRunner {

    private final ConcertService concertService;

    public ConcertTrackerApplication(ConcertService concertService) {
        this.concertService = concertService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConcertTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        concertService.loadStartingData();
        showMainMenu();
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== Concert Tracker ===");
            System.out.println("1) Concerts");
            System.out.println("2) Search concerts");
            System.out.println("3) Artists");
            System.out.println("4) Venues");
            System.out.println("5) Promoters");
            System.out.println("6) Reports");
            System.out.println("0) Quit");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1 -> listAllConcerts();
                case 4 -> venueDisplay();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Coming soon.");
            }
        }
    }

    private void listAllConcerts() {
        List<Concert> concerts = concertService.getAllConcerts();
        if (concerts.isEmpty()) {
            System.out.println("No concerts found.");
        } else {
            System.out.println("\n--- All Concerts ---");
            for (Concert c : concerts) {
                System.out.println(c);
            }
        }
    }
    private void venueDisplay() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== Venues ===");
            System.out.println("1) List all venues");
            System.out.println("2) Add a venue");
            System.out.println("3) Find by city");
            System.out.println("4) Find by name");
            System.out.println("5) Find by minimum capacity");
            System.out.println("6) Update capacity");
            System.out.println("7) Delete");
            System.out.println("0) Back");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1 -> listAllVenues();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Coming soon.");
            }
        }
    }

    private void listAllVenues() {
        List<Venue> venues = concertService.getAllVenues();
        if (venues.isEmpty()) {
            System.out.println("No venues found.");
        } else {
            System.out.println("\n--- All Venues ---");
            for (Venue v : venues) {
                System.out.println(v);
            }
        }
    }
}