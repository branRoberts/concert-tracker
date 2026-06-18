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
                case 1 -> concertDisplay();
                case 2 -> searchDisplay();
                case 3 -> artistDisplay();
                case 4 -> venueDisplay();
                case 5 -> promoterDisplay();
                case 6 -> reportsDisplay();
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
                case 2 -> addVenue(scanner);
                case 3 -> findVenuesByCity(scanner);
                case 4 -> findVenuesByName(scanner);
                case 5 -> findVenuesByMinCapacity(scanner);
                case 6 -> updateVenueCapacity(scanner);
                case 7 -> deleteVenue(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
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
    private void addVenue(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        System.out.print("Capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine().trim());
        Venue v = concertService.addVenue(name, city, capacity);
        System.out.println("Added: " + v);
    }

    private void findVenuesByCity(Scanner scanner) {
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        List<Venue> results = concertService.findVenuesByCity(city);
        if (results.isEmpty()) System.out.println("No venues found.");
        else results.forEach(System.out::println);
    }

    private void findVenuesByName(Scanner scanner) {
        System.out.print("Name contains: ");
        String name = scanner.nextLine().trim();
        List<Venue> results = concertService.findVenuesByName(name);
        if (results.isEmpty()) System.out.println("No venues found.");
        else results.forEach(System.out::println);
    }

    private void findVenuesByMinCapacity(Scanner scanner) {
        System.out.print("Minimum capacity: ");
        int cap = Integer.parseInt(scanner.nextLine().trim());
        List<Venue> results = concertService.findVenuesByMinCapacity(cap);
        if (results.isEmpty()) System.out.println("No venues found.");
        else results.forEach(System.out::println);
    }

    private void updateVenueCapacity(Scanner scanner) {
        listAllVenues();
        System.out.print("Venue ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("New capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine().trim());
        concertService.updateVenueCapacity(id, capacity);
        System.out.println("Updated.");
    }

    private void deleteVenue(Scanner scanner) {
        listAllVenues();
        System.out.print("Venue ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        concertService.deleteVenue(id);
        System.out.println("Deleted.");
    }
    private void artistDisplay() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== Artists ===");
            System.out.println("1) List all artists");
            System.out.println("2) Add an artist");
            System.out.println("3) Find by genre");
            System.out.println("4) Find by name");
            System.out.println("5) Update genre");
            System.out.println("6) Delete");
            System.out.println("0) Back");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1 -> listAllArtists();
                case 2 -> addArtist(scanner);
                case 3 -> findArtistsByGenre(scanner);
                case 4 -> findArtistsByName(scanner);
                case 5 -> updateArtistGenre(scanner);
                case 6 -> deleteArtist(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void listAllArtists() {
        List<Artist> artists = concertService.getAllArtists();
        if (artists.isEmpty()) System.out.println("No artists found.");
        else artists.forEach(System.out::println);
    }

    private void addArtist(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Genre: ");
        String genre = scanner.nextLine().trim();
        Artist a = concertService.addArtist(name, genre);
        System.out.println("Added: " + a);
    }

    private void findArtistsByGenre(Scanner scanner) {
        System.out.print("Genre: ");
        String genre = scanner.nextLine().trim();
        List<Artist> results = concertService.findArtistsByGenre(genre);
        if (results.isEmpty()) System.out.println("No artists found.");
        else results.forEach(System.out::println);
    }

    private void findArtistsByName(Scanner scanner) {
        System.out.print("Name contains: ");
        String name = scanner.nextLine().trim();
        List<Artist> results = concertService.findArtistsByName(name);
        if (results.isEmpty()) System.out.println("No artists found.");
        else results.forEach(System.out::println);
    }

    private void updateArtistGenre(Scanner scanner) {
        listAllArtists();
        System.out.print("Artist ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("New genre: ");
        String genre = scanner.nextLine().trim();
        concertService.updateArtistGenre(id, genre);
        System.out.println("Updated.");
    }

    private void deleteArtist(Scanner scanner) {
        listAllArtists();
        System.out.print("Artist ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        concertService.deleteArtist(id);
        System.out.println("Deleted.");
    }
    private void promoterDisplay() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== Promoters ===");
            System.out.println("1) List all promoters");
            System.out.println("2) Add a promoter");
            System.out.println("3) Find by name");
            System.out.println("4) Delete");
            System.out.println("0) Back");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1 -> listAllPromoters();
                case 2 -> addPromoter(scanner);
                case 3 -> findPromotersByName(scanner);
                case 4 -> deletePromoter(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void listAllPromoters() {
        List<Promoter> promoters = concertService.getAllPromoters();
        if (promoters.isEmpty()) System.out.println("No promoters found.");
        else promoters.forEach(System.out::println);
    }

    private void addPromoter(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        Promoter p = concertService.addPromoter(name);
        System.out.println("Added: " + p);
    }

    private void findPromotersByName(Scanner scanner) {
        System.out.print("Name contains: ");
        String name = scanner.nextLine().trim();
        List<Promoter> results = concertService.findPromotersByName(name);
        if (results.isEmpty()) System.out.println("No promoters found.");
        else results.forEach(System.out::println);
    }

    private void deletePromoter(Scanner scanner) {
        listAllPromoters();
        System.out.print("Promoter ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        concertService.deletePromoter(id);
        System.out.println("Deleted.");
    }
    private void concertDisplay() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== Concerts ===");
            System.out.println("1) List all concerts");
            System.out.println("2) View concert by ID");
            System.out.println("3) Add a concert");
            System.out.println("4) Update ticket price");
            System.out.println("5) Update tickets sold");
            System.out.println("6) Delete");
            System.out.println("0) Back");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1 -> listAllConcerts();
                case 2 -> viewConcertById(scanner);
                case 3 -> addConcert(scanner);
                case 4 -> updateConcertPrice(scanner);
                case 5 -> updateConcertTicketsSold(scanner);
                case 6 -> deleteConcert(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void viewConcertById(Scanner scanner) {
        System.out.print("Concert ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        concertService.getConcertById(id).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Concert not found.")
        );
    }

    private void addConcert(Scanner scanner) {
        System.out.println("\n--- Artists ---");
        concertService.getAllArtists().forEach(System.out::println);
        System.out.print("Artist ID: ");
        int artistId = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("\n--- Venues ---");
        concertService.getAllVenues().forEach(System.out::println);
        System.out.print("Venue ID: ");
        int venueId = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("\n--- Promoters ---");
        concertService.getAllPromoters().forEach(System.out::println);
        System.out.print("Promoter ID: ");
        int promoterId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Ticket price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Tickets sold: ");
        int ticketsSold = Integer.parseInt(scanner.nextLine().trim());

        Concert c = concertService.addConcert(year, price, ticketsSold, artistId, venueId, promoterId);
        if (c != null) System.out.println("Added: " + c);
    }

    private void updateConcertPrice(Scanner scanner) {
        listAllConcerts();
        System.out.print("Concert ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("New price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        concertService.updateConcertPrice(id, price);
        System.out.println("Updated.");
    }

    private void updateConcertTicketsSold(Scanner scanner) {
        listAllConcerts();
        System.out.print("Concert ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("New tickets sold: ");
        int ticketsSold = Integer.parseInt(scanner.nextLine().trim());
        concertService.updateConcertTicketsSold(id, ticketsSold);
        System.out.println("Updated.");
    }

    private void deleteConcert(Scanner scanner) {
        listAllConcerts();
        System.out.print("Concert ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        concertService.deleteConcert(id);
        System.out.println("Deleted.");
    }
    private void searchDisplay() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== Search Concerts ===");
            System.out.println("1) By year");
            System.out.println("2) By artist");
            System.out.println("3) By venue");
            System.out.println("4) By city");
            System.out.println("5) By maximum price");
            System.out.println("6) By price range");
            System.out.println("7) Advanced (max price + earliest year)");
            System.out.println("0) Back");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1 -> searchByYear(scanner);
                case 2 -> searchByArtist(scanner);
                case 3 -> searchByVenue(scanner);
                case 4 -> searchByCity(scanner);
                case 5 -> searchByMaxPrice(scanner);
                case 6 -> searchByPriceRange(scanner);
                case 7 -> searchAdvanced(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printConcertResults(List<Concert> results) {
        if (results.isEmpty()) System.out.println("No concerts found.");
        else results.forEach(System.out::println);
    }

    private void searchByYear(Scanner scanner) {
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine().trim());
        printConcertResults(concertService.findByYear(year));
    }

    private void searchByArtist(Scanner scanner) {
        System.out.print("Artist name contains: ");
        String name = scanner.nextLine().trim();
        printConcertResults(concertService.findByArtistName(name));
    }

    private void searchByVenue(Scanner scanner) {
        System.out.print("Venue name contains: ");
        String name = scanner.nextLine().trim();
        printConcertResults(concertService.findByVenueName(name));
    }

    private void searchByCity(Scanner scanner) {
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        printConcertResults(concertService.findByCity(city));
    }

    private void searchByMaxPrice(Scanner scanner) {
        System.out.print("Maximum price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        printConcertResults(concertService.findByMaxPrice(price));
    }

    private void searchByPriceRange(Scanner scanner) {
        System.out.print("Minimum price: ");
        double min = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Maximum price: ");
        double max = Double.parseDouble(scanner.nextLine().trim());
        printConcertResults(concertService.findByPriceRange(min, max));
    }

    private void searchAdvanced(Scanner scanner) {
        System.out.print("Maximum price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Earliest year: ");
        int year = Integer.parseInt(scanner.nextLine().trim());
        printConcertResults(concertService.findByMaxPriceAndEarliestYear(price, year));
    }
    private void reportsDisplay() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== Reports ===");
            System.out.println("1) Revenue per venue");
            System.out.println("2) Busiest venue and artist");
            System.out.println("3) Average ticket price by year");
            System.out.println("4) Capacity report");
            System.out.println("0) Back");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1 -> reportRevenue();
                case 2 -> reportBusiest();
                case 3 -> reportAvgPrice();
                case 4 -> reportCapacity();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void reportRevenue() {
        System.out.println("\n--- Revenue Per Venue ---");
        List<Object[]> results = concertService.getRevenuePerVenue();
        if (results.isEmpty()) System.out.println("No data.");
        else {
            for (Object[] row : results) {
                System.out.printf("%-30s $%,.2f%n", row[0], row[1]);
            }
        }
    }

    private void reportBusiest() {
        System.out.println("\n--- Busiest Venue ---");
        List<Object[]> venues = concertService.getConcertsPerVenue();
        if (!venues.isEmpty()) {
            Object[] top = venues.get(0);
            System.out.printf("%s — %d concerts%n", top[0], ((Number) top[1]).intValue());
        }

        System.out.println("\n--- Busiest Artist ---");
        List<Object[]> artists = concertService.getConcertsPerArtist();
        if (!artists.isEmpty()) {
            Object[] top = artists.get(0);
            System.out.printf("%s — %d concerts%n", top[0], ((Number) top[1]).intValue());
        }
    }

    private void reportAvgPrice() {
        System.out.println("\n--- Average Ticket Price By Year ---");
        List<Object[]> results = concertService.getAvgPriceByYear();
        if (results.isEmpty()) System.out.println("No data.");
        else {
            for (Object[] row : results) {
                System.out.printf("%d — $%.2f%n", ((Number) row[0]).intValue(), row[1]);
            }
        }
    }

    private void reportCapacity() {
        System.out.println("\n--- Capacity Report ---");
        List<Concert> concerts = concertService.getAllConcertsForCapacityReport();
        if (concerts.isEmpty()) System.out.println("No data.");
        else {
            for (Concert c : concerts) {
                double pct = (double) c.getTicketsSold() / c.getVenue().getCapacity() * 100;
                String status = pct >= 100 ? " *** SOLD OUT ***" : "";
                System.out.printf("%-45s %d/%d (%.1f%%)%s%n",
                        c.getArtist().getName() + " at " + c.getVenue().getName(),
                        c.getTicketsSold(), c.getVenue().getCapacity(), pct, status);
            }
        }
    }
}