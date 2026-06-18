# Concert Tracker

## Description
Concert Tracker is a Java console application for managing concerts, artists, venues, and promoters. Users can add and manage all four types of data, search concerts by various criteria, and view reports on revenue, attendance, and capacity.

## Features
- Manage venues, artists, promoters, and concerts through a menu-driven interface
- Add, view, update, and delete all four types of data
- Enforce business rules: concerts cannot exceed venue capacity, no negative prices or ticket counts
- Search concerts by year, artist, venue, city, price, price range, or a combination of max price and earliest year
- Reports: revenue per venue, busiest venue and artist, average ticket price by year, and capacity report

## Setup

### Prerequisites
- IntelliJ IDEA
- Java SDK 17
- MySQL

### Running the Application

1. Open IntelliJ IDEA and select "Open", then navigate to the project directory.
2. Create a MySQL database named `concert_tracker`.
3. Update `src/main/resources/application.properties` with your MySQL username and password.
4. Run the application from `ConcertTrackerApplication.java`.
5. On first run, starter data is loaded automatically.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Resources
- [Java Visual Learning Hub](https://raymaroun.github.io/yearup-java-visuals/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [W3Schools Java](https://www.w3schools.com/java/default.asp)