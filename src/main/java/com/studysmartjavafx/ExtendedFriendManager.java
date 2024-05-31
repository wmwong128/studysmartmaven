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

public class ExtendedFriendManager extends FriendManager {
    private Map<String, Set<String>> friendshipGraph;

    public ExtendedFriendManager() {
        friendshipGraph = new HashMap<>();
        loadFriendshipGraph();
    }

    // Load friendship graph from the friends.csv file
    private void loadFriendshipGraph() {
        List<String> lines = loadFriendsFromFile();
        for (String line : lines) {
            String[] parts = line.split(",");
            String user1 = parts[0];
            String user2 = parts[1];
            String status = parts[2];
            if (status.equals("accepted")) {
                addEdge(user1, user2);
            }
        }
    }

    // Adds an edge between two users in the friendship graph
    private void addEdge(String user1, String user2) {
        friendshipGraph.putIfAbsent(user1, new HashSet<>());
        friendshipGraph.putIfAbsent(user2, new HashSet<>());
        friendshipGraph.get(user1).add(user2);
        friendshipGraph.get(user2).add(user1);
    }

    // Visualize friendship graph
    public void visualizeFriendshipGraph() {
        System.out.println("Friendship Graph:");
        for (String user : friendshipGraph.keySet()) {
            System.out.print(user + " -> ");
            for (String friend : friendshipGraph.get(user)) {
                System.out.print(friend + " ");
            }
            System.out.println();
        }
    }

    // Override the acceptFriendRequest method to update the friendship graph
    @Override
    public void acceptFriendRequest(User receiver, String senderUsername) {
        super.acceptFriendRequest(receiver, senderUsername);
        addEdge(receiver.getUsername(), senderUsername);
    }

    // Override the rejectFriendRequest method to update the friendship graph
    @Override
    public void rejectFriendRequest(User receiver, String senderUsername) {
        super.rejectFriendRequest(receiver, senderUsername);
        friendshipGraph.get(receiver.getUsername()).remove(senderUsername);
        friendshipGraph.get(senderUsername).remove(receiver.getUsername());
    }
}
