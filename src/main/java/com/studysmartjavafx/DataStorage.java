package com.studysmartjavafx;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */

//userdatastoarge

import java.io.*;
import java.nio.file.*;
import java.util.*;



public class DataStorage {

    private static final String USERDATA_FILE_PATH = "Y1S2Assignment_Steffi\\src\\Y1S2Assignment\\userstorage.csv";
    private static final String PARENTCHILD_FILE_PATH = "parentchild.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        User existingUser = findUserByUsername(username);

        if (existingUser != null) {
            System.out.println("User already exists: " + existingUser);
            return;
        }

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

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

        User newUser = new User(
                email,
                username,
                password,
                role, parentUsernames, childUsernames, currentLocation);

        saveUserData(newUser);
        System.out.println("User saved: " + newUser);
    }

    private static User findUserByUsername(String username) {
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

    private static List<Map<String, String>> loadParentChildData() {
        List<Map<String, String>> parentChildList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(PARENTCHILD_FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                Map<String, String> pair = new HashMap<>();
                pair.put("parent", parts[0].trim());
                pair.put("child", parts[1].trim());
                parentChildList.add(pair);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parentChildList;
    }

    private static void addParentChildRelationship(String parent, String child) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PARENTCHILD_FILE_PATH, true))) {
            writer.write(parent + "," + child);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveUserData(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERDATA_FILE_PATH, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomCoordinate() {
        double x = Math.random() * 1000 - 500;
        double y = Math.random() * 1000 - 500;
        return "(" + x + ", " + y + ")";
    }
}
