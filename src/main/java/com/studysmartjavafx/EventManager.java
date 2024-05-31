package com.studysmartjavafx;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;


//only display Live Events(happenning on current day) & Closest 3 Upcoming Events
//Young students can register themselves for any events displayed & gain 5 points per event
//Display error message if event clashed

//Only EDUCATOR CREATE 

/*DS
Generics
ArrayList  */

public class EventManager {

    int numOfEventsCreated=10;
    makeEvents m = new makeEvents();
    
    //Create myself 10 events
    public void createOwnEvent(){
        m.addEvent("Science Olympaid", "Participate in challenging science competitions covering various disciplines.", "Technology Park Malaysia"
                , "14/6/2024", "09:00-18:00");
        
        m.addEvent("Science Fair", "Showcase your scientific experiments and discoveries.", "UM DTC"
                , "14/6/2024", "09:00-22:00");
        
        m.addEvent("Hackathon", "Compete in a fast-paced coding competition to develop innovative solutions", "UM FSKTM", 
                "14/6/2024", "09:00-15:00");
        
        m.addEvent("Weekly Coding Camp", "Learn programming languages and coding techniques", "UM FSKTM", 
                "15/6/2024", "09:00-10:00");
        
        m.addEvent("Technology Expo", "Discover the latest technological innovations and advancements.", "Technology Park Malaysia", 
                "15/6/2024", "09:00-22:00");
        
        m.addEvent("Data Science Conference", "Explore the world of data science.", "UM FSKTM", "16/6/2024", 
                "09:00-15:00");
        
        m.addEvent("Engineering Design Challenge", "Design and build solutions to real-world engineering problems in a team setting.", 
                "UM FOE", "16/6/2024", "09:00-15:00");
        
        m.addEvent("Mathematics Competition", "Test students' mathematical skills.", "UM DTC", "16/6/2024", "09:00-18:00");
        
        m.addEvent("Musuem Exploration", "Dive into STEM history and innovation through interactive museum exhibits.", 
                "Petronas, The Discovery Centre", "17/6/2024", "09:00-12:00");
        
        m.addEvent("STEM Speaker Series", "Listen to inspiring talks from STEM experts.", "Technology Park Malaysia", 
                "18/6/2024", "09:00-12:00");
    }
    
    
    //Create event
    //Accept user input
    Scanner s = new Scanner(System.in);
    public void userCreateEvent(){
        System.out.println("Event "+(this.numOfEventsCreated + 1) + ":");
        System.out.print("Title - ");
        String title = s.nextLine() ;
        System.out.print("Description - ");
        String description = s.nextLine();
        System.out.print("Venue - ");
        String venue = s.nextLine();
        System.out.print("Date - ");
        String date = s.nextLine();
        System.out.print("Time - ");
       String time = s.nextLine();
         
       m.addEvent(title, description, venue, date, time);
        numOfEventsCreated++;
        
        System.out.println();
        DisplayCreatedEventNo();
    }
    
    
  //Display no. of events created 
  //Need to update after user created events
    public void DisplayCreatedEventNo(){
        System.out.println("Total Number of Events Created: "+numOfEventsCreated);
    }
    
    public void studentJoinEvent(){
    
}
    

    class Event{

        String title;
        String description;
        String venue;
        String date;
        String time;
        String eventName;
        LocalDate eventDate;

        public Event(String eventName, LocalDate eventDate) {
            this.eventName = eventName;
            this.eventDate = eventDate;
        }
        
        public Event(String title, String description, String venue, String date, String time) {
            this.title = title;
            this.description = description;
            this.venue = venue;
            this.date = date;
            this.time = time;
        }

        public String getEventName() {
            return eventName;
        }

        public LocalDate getEventDate() {
            return eventDate;
        }
        
        
        
        @Override
        public String toString() {
            return "Event " + (m.events.indexOf(this) + 1) + ":" +
                   "\nTitle - " + title +
                   "\nDescription - " + description +
                   "\nVenue - " + venue +
                   "\nDate - " + date +
                   "\nTime - " + time + "\n";
        }
        
    }
    
    class makeEvents{
        
        ArrayList<Event> events;
        
        makeEvents(){
            events = new ArrayList<>(); //Create Arraylist object everytime u create makeEvents object
        }
        
        public void addEvent(String title, String description, String venue, String date, String time){
            Event events1 = new Event(title, description, venue, date, time); //Create Event object
            events.add(events1);
        }
        
       public ArrayList<Event> getLiveEvents(LocalDate today) {
        return (ArrayList<Event>) events.stream()
                .filter(event -> event.getEventDate().equals(today))
                .collect(Collectors.toList());
    }
       
       public ArrayList<Event> getClosestUpcomingEvents(LocalDate today, int limit) {
        return (ArrayList<Event>) events.stream()
                .filter(event -> event.getEventDate().isAfter(today))
                .sorted(Comparator.comparing(Event::getEventDate))
                .limit(limit)
                .collect(Collectors.toList());
    }

       public void displayEvents(LocalDate today) {
        ArrayList<Event> liveEvents = getLiveEvents(today);
        ArrayList<Event> upcomingEvents = getClosestUpcomingEvents(today, 3);

        System.out.println("Live Events:");
        for (Event event : liveEvents) {
            System.out.println(event.getEventName() + " on " + event.getEventDate());
        }

        System.out.println("Closest 3 Upcoming Events:");
        for (Event event : upcomingEvents) {
            System.out.println(event.getEventName() + " on " + event.getEventDate());
        }

    }
        
        public void displayEvents() {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    


    
    
}
