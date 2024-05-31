package com.studysmartjavafx;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
/*public class User {
    
    private String email;
    private String username;
    private String password;
    private String role;
    private String[] parents;
    private String[] children;
    private String locationCoordinate;
    //private int currentPoints;
    //private int quizzesCreated;
    //private int eventsCreated;
    //private String pastBookings;
    //private String friends;

    // Constructor
    public User(String email, String username, String password, String role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locationCoordinate = generateRandomCoordinate();
        this.currentPoints = 0;
        //this.quizzesCreated = quizzesCreated;
        //this.eventsCreated = eventsCreated;
        //this.pastBookings = pastBookings;
        //this.friends = friends;
    }

    public String getEmail() {
        return email;
    }

   // public int getQuizzesCreated() {
     //   return quizzesCreated;
   // }

    //public int getEventsCreated() {
      //  return eventsCreated;
    //}

    //public String getPastBookings() {
      //  return pastBookings;
    //}

    //public String getFriends() {
        return friends;
    //}



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String[] getParents() {
        return parents;
    }

    public String[] getChildren() {
        return children;
    }

    public String getLocationCoordinate() {
        return locationCoordinate;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    

    private String generateRandomCoordinate() {
        double x = Math.random() * 1000 - 500;
        double y = Math.random() * 1000 - 500;
        return "(" + x + ", " + y + ")";
    }
}*/




/////////// new one
import java.util.*;
import jdk.jfr.Event;

public class User {
    private String email;
    private String username;
    private String password; // Hashed password
    private String role;
    private List<String> parentUsernames;
    private List<String> childUsernames;
    private String currentLocation;
    private String timestamp;
     private List<Event> userEvents; // List of events for the user
    private List<User> children; // List of children users
     private List<Discussion> discussions;
 
    public User(String email, String username, String password, String role, List<String> parentUsernames1, List<String> childUsernames1, String currentLocation1) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.parentUsernames = new ArrayList<>();
        this.childUsernames = new ArrayList<>();
        this.currentLocation = "";
        this.timestamp = new Date().toString();
        
        
    }
    
      public List<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(List<Discussion> discussions) {
        this.discussions = discussions;
    }
// Getter method for userEvents
    public List<Event> getUserEvents() {
        return userEvents;
    }

    // Getter method for children
    public List<User> getChildren() {
        return children;
    }
    // Getters and setters for currentLocation
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    // Remaining getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public List<String> getParentUsernames() {
        return parentUsernames;
    }

    public List<String> getChildUsernames() {
        return childUsernames;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "\nEmail: " + email + "\n"
                + "Username: " + username + "\n"
                + "Role: " + role + "\n"
                + "Parents: " + parentUsernames + "\n"
                + "Children: " + childUsernames + "\n"
                + "Current Location: " + currentLocation + "\n"
                + "Timestamp: " + timestamp + "\n";
    }

    public String toCsvString() {
        return email + "," + username + "," + password + "," + role + "," +
               parentUsernames + "," + childUsernames + "," + currentLocation + "," +
               timestamp;
    }

    public static User fromString(String userString) {
        String[] parts = userString.split(",");
        String email = parts[0];
        String username = parts[1];
        String password = parts[2];
        String role = parts[3];
        List<String> parentUsernames = Arrays.asList(parts[4].replace("[", "").replace("]", "").split("\\s*,\\s*"));
        List<String> childUsernames = Arrays.asList(parts[5].replace("[", "").replace("]", "").split("\\s*,\\s*"));
        String currentLocation = parts[6];
        String timestamp = parts[7];
        
       return new User(email, username, password, role, parentUsernames, childUsernames, currentLocation);
    }
    
     // Method to check if the user or any of their children have an event on a specific date
    public boolean hasEventOnDate(String date) {
        // Check the user's events
        if (userEvents != null) {
            for (Event eventDate : userEvents) {
                if (eventDate.equals(date)) {
                    return true;
                }
            }
        }
        // Check the children's events
        if (children != null) {
            for (User child : children) {
                if (child.hasEventOnDate(date)) {
                    return true;
                }
            }
        }
        return false;
    }
}
