package com.studysmartjavafx;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.util.List;

public class ViewProfile {
    //event not yet done
    private EventManager eventManager;
    private BookingManager bookingManager;
    private FriendManager friendManager;

    //havent got event manager
    public ViewProfile(EventManager eventManager, BookingManager bookingManager, FriendManager friendManager) {
        this.eventManager = eventManager;
        this.bookingManager = bookingManager;
        this.friendManager = friendManager;
    }

    public void displayUserProfile(User user) {
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Role: " + user.getRole());
        System.out.println("Parents: " + user.getParentUsernames());
        System.out.println("Children: " + user.getChildUsernames());
        System.out.println("Current Location: " + user.getCurrentLocation());
        System.out.println("Timestamp: " + user.getTimestamp());
    }

    public void displayDiscussionPage(User user) {
        List<Discussion> discussions = user.getDiscussions();
        if (discussions == null || discussions.isEmpty()) {
            System.out.println("No discussions available.");
            return;
        }

        System.out.println("Discussions:");
        for (Discussion discussion : discussions) {
            System.out.println("Topic: " + discussion.getTopic());
            System.out.println("Message: " + discussion.getMessage());
            System.out.println("Timestamp: " + discussion.getTimestamp());
            System.out.println("---------------------------");
        }
    }

    public void viewProfile(User user) {
        System.out.println("\nProfile Information:");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Role: " + user.getRole());
        System.out.println("Location Coordinate: " + user.getCurrentLocation());

        switch (user.getRole().toLowerCase()) {
            case "educator":
                QuizManager quizManager = new QuizManager();
                EventManager eventManager = new EventManager();
//System.out.println("Number of Quizzes Created: " + eventManager.getNumQuizzesCreated(loggedInUser));            
//System.out.println("Number of Events Created: " + eventManager.getNumEventsCreated(loggedInUser)); 
                quizManager.DisplayCreatedQuizNo();
                eventManager.DisplayCreatedEventNo();
                break;
            case "parent":
                System.out.println("Past Bookings Made: ");
                bookingManager.displayPastBookings(user);
                break;
            case "young student":
                System.out.println("Points: " + eventManager.getPoints(user));
                System.out.println("Friends: " + friendManager.getFriends(user));
                break;
            default:
                System.out.println("Invalid role.");
                break;
        }
    }
}



    

