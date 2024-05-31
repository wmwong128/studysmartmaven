package com.studysmartjavafx;
import java.util.*;

public class CLIbase {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginRegister loginRegister = new LoginRegister();
        EventManager eventManager = new EventManager();
        BookingManager bookingManager = new BookingManager();
        FriendManager friendManager = new FriendManager();
        ViewProfile viewProfile = new ViewProfile(eventManager, bookingManager, friendManager);
        QuizManager quizManager = new QuizManager(); 
        ExtendedFriendManager friendManagerS = new ExtendedFriendManager();
        EventManager createEvent = new EventManager();

        
        System.out.println("Welcome to StudySmart(STEM)! Please choose an option: ");
        System.out.println("1. Register");
        System.out.println("2. Login");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (choice == 1) {
            loginRegister.registerUser(scanner);
        } else if (choice == 2) {
            User loggedInUser = loginRegister.loginUser(scanner);
            if (loggedInUser != null) {
                viewProfile.viewProfile(loggedInUser);

                // Display role-specific information
                switch (loggedInUser.getRole().toLowerCase()) {
                    case "educator":
                        //System.out.println("Number of Quizzes Created: " + eventManager.getNumQuizzesCreated(loggedInUser));
                        //System.out.println("Number of Events Created: " + eventManager.getNumEventsCreated(loggedInUser));
                        quizManager.DisplayCreatedQuizNo();
                        eventManager.DisplayCreatedEventNo();
                        break;
                    case "parent":
                        System.out.println("Past Bookings: ");
                        bookingManager.displayPastBookings(loggedInUser);
                        break;
                    case "young student":
                       // System.out.println("Points: " + eventManager.getPoints(loggedInUser));
                        System.out.println("Friends: " + friendManager.getFriends(loggedInUser));
                        break;
                    default:
                        System.out.println("Invalid role.");
                        break;
                }

                // Main menu loop for further actions
                boolean exit = false;
                while (!exit) {
                    System.out.println("\nPlease choose an option:");
                    System.out.println("1. View Event Page");
                    System.out.println("2. View Personal Profile");
                    System.out.println("3. View Other Profile");
                    System.out.println("4. Discussion Page");

                    switch (loggedInUser.getRole().toLowerCase()) {
                        case "educator":
                            System.out.println("5. Create Event"); 
                            System.out.println("6. Create Quiz"); 
                            break;
                        case "parent":
                            System.out.println("5. Make Booking"); 
                            break;
                        case "young student":
                            System.out.println("5. Attempt Quiz"); 
                            System.out.println("6. Request and Manage Friends"); 
                            System.out.println("7. View Global Leaderboard");
                            System.out.println("8. Register for Events");
                            break;
                    }

                    System.out.println("0. Logout");
                    //System.out.println("99. Back to Previous Menu");
                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (option) {
                        case 1:
                            createEvent.createOwnEvent();
                            eventManager.m.displayEvents();
                            break;
                        case 2:
                            viewProfile.viewProfile(loggedInUser);
                            break;
                        case 3:
                            System.out.println("Enter username to view profile:");
                            String username = scanner.nextLine();
                            User otherUser = loginRegister.findUserByUsername(username);
                            if (otherUser != null) {
                                viewProfile.viewProfile(otherUser);
                            } else {
                                System.out.println("User not found.");
                            }
                            break;
                        case 4:
                            viewProfile.displayDiscussionPage(loggedInUser);
                            break;
                        case 5:
                            if (loggedInUser.getRole().equalsIgnoreCase("educator")) {
                                while(true){
                                    System.out.print("\nCreate an Event? Yes[1]/No[0]: ");
                                    int response = scanner.nextInt();
                                    if(response==1){
                                        System.out.println();
                                        eventManager.userCreateEvent();
                                    }
                                    else{
                                        break;
                                    }
                                }
                                
                                eventManager.DisplayCreatedEventNo();
                                //eventdatabase
                            } else if (loggedInUser.getRole().equalsIgnoreCase("parent")) {
                                bookingManager.displayDestinations(loggedInUser);
                                System.out.println("Enter a time slot number:");
                                int timeSlot = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                System.out.println("Enter child's name for booking:");
                                String childName = scanner.nextLine();
                                BookingDestination selectedDestination = bookingManager.getDestinations().get(timeSlot - 1);
                                bookingManager.saveBooking(loggedInUser.getUsername(), childName, selectedDestination);
                            } else if (loggedInUser.getRole().equalsIgnoreCase("young student")) {
                                AnswerQuiz answerquiz = new AnswerQuiz();
                                System.out.println("Number of Question Attempted: "+answerquiz.quizQuestions.size());
                                
                            }
                            break;
                        case 6:
                            if (loggedInUser.getRole().equalsIgnoreCase("educator")) {
                                //Accept users' input
                                       while(true){
                                    System.out.print("\nCreate a Quiz? Yes[1]/No[0]: ");
                                    int response = scanner.nextInt();
                                    if(response==1){
                                        System.out.println();
                                        quizManager.userCreateQuiz();
                                    }
                                    else{
                                        break;
                                    }
                                }
                                       quizManager.DisplayCreatedQuizNo();
                            } else if (loggedInUser.getRole().equalsIgnoreCase("young student")) {
                                System.out.println("1. Send Friend Request");
                                System.out.println("2. Accept Friend Request");
                                System.out.println("3. Reject Friend Request");
                                System.out.println("4. View All Friends");
                                System.out.println("5. View Friend Requests");
                                

                                int friendOption = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                switch (friendOption) {
                                    case 1:
                                        System.out.println("Enter friend's username to send request:");
                                        String friendUsername = scanner.nextLine();
                                        friendManager.sendFriendRequest(loggedInUser, friendUsername);
                                        break;
                                    case 2:
                                        System.out.println("Enter the username of the friend request you want to accept:");
                                        String senderUsername = scanner.nextLine();
                                        friendManager.acceptFriendRequest(loggedInUser, senderUsername);
                                        break;
                                    case 3:
                                        System.out.println("Enter the username of the friend request you want to reject:");
                                        String rejectSenderUsername = scanner.nextLine();
                                        friendManager.rejectFriendRequest(loggedInUser, rejectSenderUsername);
                                        break;
                                    case 4:
                                        List<String> friends = friendManager.getFriends(loggedInUser);
                                        System.out.println("Your Friends:");
                                        for (String friend : friends) {
                                            System.out.println("- " + friend);
                                            friendManagerS.visualizeFriendshipGraph(); //can show them thier freiendship graph
                                        }
                                        break;
                                            case 5:
                                        // Implement viewing friend requests
                                        List<String> friendRequests = friendManager.getFriendRequests(loggedInUser);
                                        if (friendRequests.isEmpty()) {
                                            System.out.println("You have no pending friend requests.");
                                        } else {
                                            System.out.println("Pending friend requests:");
                                            for (String request : friendRequests) {
                                                System.out.println("- " + request);
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println("Invalid option.");
                                        break;
                                        
                                    case 8: //Student Register Event
                                        // bookingManager.studentRegisterEvent(event);
                                        //have to link to csv//sql
                                }
                            }
                            break;
                            
                        case 0:
                            exit = true;
                            break;
                            //not sure can go back or not
                         /*case 99:
                            System.out.println("Going back to previous menu...");
                            break;   */
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                }
            }
        } else {
            System.out.println("Invalid choice. Exiting.");
        }

 
    }
}
