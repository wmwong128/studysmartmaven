package com.studysmartjavafx;

import java.util.*;

public class AccessManager {
 
    public static void performAccessManagement(User user, Scanner scanner) {
        System.out.println("Access Management:");

        switch (user.getRole()) {
            case "Educator":
                accessEducatorPages(scanner);
                break;
            case "Parent":
                accessParentPages(scanner);
                break;
            case "Young Student":
                accessYoungStudentPages(scanner);
                break;
            default:
                System.out.println("Unknown role. No specific access granted.");
                break;
        }
    }

    private static void accessEducatorPages(Scanner scanner) {
        System.out.println("1. Create Event Page");
        System.out.println("2. Create Quiz Page");
        System.out.println("3. Event Page");
        System.out.println("4. View Personal/Others' Profile");
        System.out.println("5. Discussion Page");
        System.out.println("6. Exit Access Management");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                System.out.println("You have access to Create Event Page.");
                // Add code to navigate to Create Event Page
                break;
            case 2:
                System.out.println("You have access to Create Quiz Page.");
                // Add code to navigate to Create Quiz Page
                break;
            case 3:
                accessEventPage();
                break;
            case 4:
                accessProfilePage();
                break;
            case 5:
                accessDiscussionPage();
                break;
            case 6:
                System.out.println("Exiting Access Management.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void accessParentPages(Scanner scanner) {
        System.out.println("1. Booking Page");
        System.out.println("2. Event Page");
        System.out.println("3. View Personal/Others' Profile");
        System.out.println("4. Discussion Page");
        System.out.println("5. Exit Access Management");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                System.out.println("You have access to Booking Page.");
                // Add code to navigate to Booking Page
                break;
            case 2:
                accessEventPage();
                break;
            case 3:
                accessProfilePage();
                break;
            case 4:
                accessDiscussionPage();
                break;
            case 5:
                System.out.println("Exiting Access Management.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

private static void accessYoungStudentPages(Scanner scanner) {
    System.out.println("1. Quiz Page");
    System.out.println("2. View Personal/Others' Profile");
    System.out.println("3. Add Friend");
    System.out.println("4. Discussion Page");
    System.out.println("5. Exit Access Management");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    switch (choice) {
        case 1:
            System.out.println("You have access to Quiz Page.");
            break;
        case 2:
            accessProfilePage();
            break;
        case 3:
            addFriend(scanner);
            break;
        case 4:
            accessDiscussionPage();
            break;
        case 5:
            System.out.println("Exiting Access Management.");
            break;
        default:
            System.out.println("Invalid choice.");
            break;
    }
}

private static void addFriend(Scanner scanner) {
    System.out.print("Would you like to add a friend? (yes/no): ");
    String response = scanner.nextLine();
    if (response.equalsIgnoreCase("yes")) {
        System.out.print("Enter the username of the friend you want to add: ");
        String friendUsername = scanner.nextLine();
        // Code to send friend request and handle accordingly
    }
}


    private static void accessEventPage() {
        System.out.println("You have access to Event Page.");
        // Add code to navigate to Event Page
    }

    private static void accessProfilePage() {
        System.out.println("You have access to View Personal/Others' Profile.");
        // Add code to navigate to Profile Page
    }

    private static void accessDiscussionPage() {
        System.out.println("You have access to Discussion Page.");
        // Add code to navigate to Discussion Page
    }
}


