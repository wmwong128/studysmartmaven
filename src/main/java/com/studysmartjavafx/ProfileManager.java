package com.studysmartjavafx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProfileManager {

    public static void viewProfile(String emailOrUsername) {
        String csvFile = "userdata.csv";
        String line;
        String[] userData;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                userData = line.split(",");
                if (userData[0].equals(emailOrUsername) || userData[1].equals(emailOrUsername)) {
                    displayProfile(userData);
                    return;
                }
            }
            System.out.println("User not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayProfile(String[] userData) {
        System.out.println("Profile Information:");
        System.out.println("Email: " + userData[0]);
        System.out.println("Username: " + userData[1]);
        System.out.println("Role: " + userData[3]);
        System.out.println("Location Coordinate: " + userData[6]);

        if (userData[3].equals("Educator")) {
            System.out.println("Number of Quizzes Created: " + userData[8]);
            System.out.println("Number of Events Created: " + userData[9]);
        } else if (userData[3].equals("Parent")) {
            System.out.println("Past Bookings Made: " + userData[10]);
        } else if (userData[3].equals("Young Student")) {
            System.out.println("Points: " + userData[7]);
            System.out.println("Friends: " + userData[11]);
        }
    }

    /*public static void main(String[] args) {
        viewProfile("user1@example.com");
        viewProfile("parent1");
        viewProfile("educator@example.com");
        viewProfile("unknown@example.com");
    }*/
}
 

