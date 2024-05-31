package com.studysmartjavafx;

import com.studysmartjavafx.PasswordHashing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LoginRegister {
    private static final String USERDATA_FILE_PATH = "userstorage.csv";
    private static final String PARENTCHILD_FILE_PATH = "parentchild.txt";

    public void registerUser(Scanner scanner) {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        if (findUserByUsername(username) != null) {
            System.out.println("Username already exists. Please try again.");
            return;
        }

        System.out.println("Enter password: ");
        String password = PasswordHashing.hashPassword(scanner.nextLine());

        System.out.println("Enter role (Parent/Young Student/Educator): ");
        String role = scanner.nextLine();

        List<String> parentUsernames = new ArrayList<>();
        List<String> childUsernames = new ArrayList<>();

        if (role.equalsIgnoreCase("Parent")) {
            parentUsernames.add(username);
            System.out.println("Enter your children's usernames (comma-separated, e.g., child1,child2): ");
            String[] children = scanner.nextLine().split(",");
            for (String child : children) {
                childUsernames.add(child.trim());
                addParentChildRelationship(username, child.trim());
            }
        } else if (role.equalsIgnoreCase("Young Student")) {
            childUsernames.add(username);
            System.out.println("Enter your parents' usernames (comma-separated, max 2, e.g., parent1,parent2): ");
            String[] parents = scanner.nextLine().split(",");
            for (String parent : parents) {
                if (parentUsernames.size() < 2) {
                    parentUsernames.add(parent.trim());
                    addParentChildRelationship(parent.trim(), username);
                }
            }
        } else if (!role.equalsIgnoreCase("Educator")) {
            System.out.println("Invalid role. Exiting.");
            return;
        }

        String currentLocation = generateRandomCoordinate();

        User newUser = new User(email, username, password, role, parentUsernames, childUsernames, currentLocation);

        saveUserData(newUser);
        System.out.println("Registration successful! User details: " + newUser);
    }

    public User loginUser(Scanner scanner) {
    System.out.println("Enter username: ");
    String username = scanner.nextLine();

    User existingUser = findUserByUsername(username);
    if (existingUser == null) {
        System.out.println("User not found. Please register first.");
        return null;
    }

    System.out.println("Enter password: ");
    String password = scanner.nextLine();

    if (PasswordHashing.verifyPassword(password, existingUser.getPassword())) {
        System.out.println("Login successful!");
        return existingUser;
    } else {
        System.out.println("Incorrect password. Please try again.");
        return null; // Return null when login fails
    }
}


    User findUserByUsername(String username) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(USERDATA_FILE_PATH));
            for (String line : lines) {
                User user = User.fromString(line);
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addParentChildRelationship(String parent, String child) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PARENTCHILD_FILE_PATH, true))) {
            writer.write(parent + "," + child);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUserData(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERDATA_FILE_PATH, true))) {
            writer.write(user.toCsvString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateRandomCoordinate() {
        double x = Math.random() * 1000 - 500;
        double y = Math.random() * 1000 - 500;
        return "(" + x + ", " + y + ")";
    }
}

    