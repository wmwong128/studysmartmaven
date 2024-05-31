package com.studysmartjavafx;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class BookingDestination {
    
    private String name;
    private double x;
    private double y;

    BookingDestination(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

public class BookingManager {
    private static final String BOOKINGS_FILE_PATH = "bookings.csv";
    private static final String DESTINATIONS_FILE_PATH = "BookingDestination.txt";

    private List<BookingDestination> destinations;
    private Scanner scanner; // Declare Scanner here

    public BookingManager() {
        this.scanner = scanner; // Initialize Scanner in the constructor
        this.destinations = loadDestinationsFromFile();
    }
    
     public List<BookingDestination> getDestinations() {
        return destinations;
    }
    
     public boolean studentRegisterEvent(EventManager.Event event) { //Student register event 
        if (YoungStudent.registeredEventDates.contains(event.getEventDate())) { //clashed
            System.out.println("Error: Event on " + event.getEventDate() + " clashes with another registered event.");
            return false;
        } else { //not clashed, so register event
            YoungStudent.registeredEventDates.add(event.getEventDate());
            YoungStudent.PointSystem pointSystem = new YoungStudent.PointSystem();
            pointSystem.addPoints(5);
            System.out.println(YoungStudent.studentName + " registered for " + event.getEventName() + " on " + event.getEventDate());
            return true;
        }
    }
     
public void displayDestinations(User user) {
    System.out.println("Booking Page");
    System.out.println("=========================================================================");
    List<BookingDestination> sortedDestinations = sortDestinationsByDistance(user);
    for (int i = 0; i < sortedDestinations.size(); i++) {
        BookingDestination destination = sortedDestinations.get(i);
        double distance = calculateDistance(user.getCurrentLocation(), destination);
        System.out.println("[" + (i + 1) + "] " + destination.getName());
        System.out.println(distance + " km away");
    }
    
    LocalDate currentDate = LocalDate.now();
    LocalDate endDate = currentDate.plusDays(7); // Display time slots for one week from the current day
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    System.out.println("Enter destination ID for booking: ");
    int destinationId = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    System.out.println("=========================================================================");
    System.out.println("Selected booking for: " + sortedDestinations.get(destinationId - 1).getName());
    System.out.println("Available Time Slots:");
    int counter = 1;
    for (LocalDate date = currentDate; date.isBefore(endDate); date = date.plusDays(1)) {
        if (!user.hasEventOnDate(date.format(formatter))) {
            System.out.println("[" + counter + "] " + date.format(formatter));
            counter++;
        }
    }
    System.out.println("Enter a time slot:");
}


    private List<BookingDestination> loadDestinationsFromFile() {
        List<BookingDestination> destinations = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(DESTINATIONS_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                // String[] parts = line.split(",");
                // String name = parts[0];
                // double x = Double.parseDouble(parts[1]);
                // double y = Double.parseDouble(parts[2]);
                // destinations.add(new BookingDestination(name, x, y));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Booking destinations file not found.");
        }
        return destinations;
    }

    private List<BookingDestination> sortDestinationsByDistance(User user) {
        List<BookingDestination> sortedDestinations = new ArrayList<>(destinations);
        sortedDestinations.sort(Comparator.comparingDouble(destination ->
                calculateDistance(user.getCurrentLocation(), destination)));
        return sortedDestinations;
    }

    private double calculateDistance(String userLocation, BookingDestination destination) {
        // Assuming userLocation and destination coordinates are in the format "X, Y"
        String[] userCoordinates = userLocation.split(", ");
        double userX = Double.parseDouble(userCoordinates[0]);
        double userY = Double.parseDouble(userCoordinates[1]);
        double destinationX = destination.getX();
        double destinationY = destination.getY();
        return Math.sqrt(Math.pow(userX - destinationX, 2) + Math.pow(userY - destinationY, 2));
    }

    // Method to save booking details to CSV file
    public void saveBooking(String parentName, String childName, BookingDestination destination) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE_PATH, true))) {
            writer.write(parentName + "," + childName + "," + destination.getName() + "," + new Date() + "\n");
        } catch (IOException e) {
            System.err.println("Error saving booking: " + e.getMessage());
        }
    }

    // Method to display past bookings for a user
    public void displayPastBookings(User user) {
        try (Scanner scanner = new Scanner(new File(BOOKINGS_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String parentName = parts[0];
                String childName = parts[1];
                if (parentName.equals(user.getUsername())) {
                    System.out.println("Booking for Child: " + childName + ", Destination: " + parts[2] + ", Date: " + parts[3]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Past bookings file not found.");
        }
    }
}