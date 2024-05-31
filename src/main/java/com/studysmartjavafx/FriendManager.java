package com.studysmartjavafx;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.io.*;
import java.util.*;

public class FriendManager {
    private static final String FRIENDS_FILE_PATH = "friends.csv";

     // Creates the friends.csv file if it doesn't exist
    private void createFriendsFile() {
        try {
            File file = new File(FRIENDS_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created friends.csv file.");
            }
        } catch (IOException e) {
            System.err.println("Error creating friends.csv file: " + e.getMessage());
        }
    }
    // Sends a friend request from sender to receiver
    public void sendFriendRequest(User sender, String receiverUsername) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FRIENDS_FILE_PATH, true));
            writer.write(sender.getUsername() + "," + receiverUsername + ",pending\n");
            writer.close();
            System.out.println("Friend request sent.");
        } catch (IOException e) {
            System.err.println("Error sending friend request: " + e.getMessage());
        }
    }

    // Accepts a friend request from sender by receiver
    public void acceptFriendRequest(User receiver, String senderUsername) {
        List<String> lines = loadFriendsFromFile();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FRIENDS_FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts[0].equals(senderUsername) && parts[1].equals(receiver.getUsername()) && parts[2].equals("pending")) {
                    writer.write(senderUsername + "," + receiver.getUsername() + ",accepted\n");
                    System.out.println("Friend request accepted.");
                } else {
                    writer.write(line + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error accepting friend request: " + e.getMessage());
        }
    }

    // Rejects a friend request from sender by receiver
    public void rejectFriendRequest(User receiver, String senderUsername) {
        List<String> lines = loadFriendsFromFile();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FRIENDS_FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (!(parts[0].equals(senderUsername) && parts[1].equals(receiver.getUsername()) && parts[2].equals("pending"))) {
                    writer.write(line + "\n");
                }
            }
            writer.close();
            System.out.println("Friend request rejected.");
        } catch (IOException e) {
            System.err.println("Error rejecting friend request: " + e.getMessage());
        }
    }

    // Gets the list of friends of a user
    public List<String> getFriends(User user) {
        List<String> friends = new ArrayList<>();
        List<String> lines = loadFriendsFromFile();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equals(user.getUsername()) && parts[2].equals("accepted")) {
                friends.add(parts[1]);
            } else if (parts[1].equals(user.getUsername()) && parts[2].equals("accepted")) {
                friends.add(parts[0]);
            }
        }
        return friends;
    }

    // Gets the list of pending friend requests received by a user
    public List<String> getFriendRequests(User user) {
        List<String> friendRequests = new ArrayList<>();
        List<String> lines = loadFriendsFromFile();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[1].equals(user.getUsername()) && parts[2].equals("pending")) {
                friendRequests.add(parts[0]);
            }
        }
        return friendRequests;
    }

    // Saves friendship to the friends.csv file
    private void saveFriendship(String user1, String user2, String status) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FRIENDS_FILE_PATH, true));
            writer.write(user1 + "," + user2 + "," + status + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving friendship: " + e.getMessage());
        }
    }

    // Loads the list of friendships from the friends.csv file
    protected List<String> loadFriendsFromFile() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FRIENDS_FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error loading friends: " + e.getMessage());
        }
        return lines;
    }
}

