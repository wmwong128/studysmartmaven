package com.studysmartjavafx;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.ArrayList;


public class YoungStudent {
    
    public static String studentName; 
    public static int currentPoints;
    public static long pointLastUpdated;
    public static Set<LocalDate> registeredEventDates; 

    public YoungStudent() {
    }

    public YoungStudent(String studentName) {
        this.studentName = studentName;
        this.currentPoints = 0;
        this.pointLastUpdated = System.currentTimeMillis();
        this.registeredEventDates = new HashSet<>();
    }

    public String getStudentName() {
        return studentName;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public long getPointLastUpdated() {
        return pointLastUpdated;
    }

   

    // Inner class Leaderboard
    public static class Leaderboard {
        private ArrayList<YoungStudent> students;

        public Leaderboard() {
            this.students = new ArrayList<>();
        }

        public void addStudent(YoungStudent student) {
            students.add(student);
        }

        public void displayLeaderboard() {
            students = mergeSort(students);

            System.out.println("Leaderboard:");
            for (YoungStudent student : students) {
                System.out.println("Username: " + student.getStudentName() + ", Points: " + student.getCurrentPoints());
            }
        }

        private int compareStudents(YoungStudent s1, YoungStudent s2) {
            if (s1.getCurrentPoints() != s2.getCurrentPoints()) {
                return s2.getCurrentPoints() - s1.getCurrentPoints();
            } else {
                return Long.compare(s1.getPointLastUpdated(), s2.getPointLastUpdated());
            }
        }

        private ArrayList<YoungStudent> mergeSort(ArrayList<YoungStudent> students) {
            if (students.size() <= 1) {
                return students;
            }

            int mid = students.size() / 2;
            ArrayList<YoungStudent> left = new ArrayList<>(students.subList(0, mid));
            ArrayList<YoungStudent> right = new ArrayList<>(students.subList(mid, students.size()));

            return merge(mergeSort(left), mergeSort(right));
        }

        private ArrayList<YoungStudent> merge(ArrayList<YoungStudent> left, ArrayList<YoungStudent> right) {
            ArrayList<YoungStudent> merged = new ArrayList<>();
            int i = 0, j = 0;

            while (i < left.size() && j < right.size()) {
                if (compareStudents(left.get(i), right.get(j)) <= 0) {
                    merged.add(left.get(i));
                    i++;
                } else {
                    merged.add(right.get(j));
                    j++;
                }
            }

            while (i < left.size()) {
                merged.add(left.get(i));
                i++;
            }

            while (j < right.size()) {
                merged.add(right.get(j));
                j++;
            }

            return merged;
        }
    }

    // Inner class PointSystem
    public static class PointSystem {
        
        private ArrayList<YoungStudent> students;

        public PointSystem() {
            this.students = new ArrayList<>();
        }

        public void addPoints(int points) {
        YoungStudent.currentPoints += points; //save currentPoints
        pointLastUpdated = System.currentTimeMillis();
    }
        
        public void displayPoint(String studentName) {
        for (YoungStudent student : students) {
            if (student.getStudentName().equals(studentName)) {
                System.out.println("Username: " + student.getStudentName() + ", Points: " + student.getCurrentPoints());
                return;
            }
        }
        System.out.println("Student with username " + studentName + " not found.");
    }
        
       /* public void addStudent(YoungStudent student) {
            students.add(student);
        } */

        public void displayLeaderboard() {
            students.sort(Comparator.comparingInt(YoungStudent::getCurrentPoints).reversed()
                    .thenComparingLong(YoungStudent::getPointLastUpdated));

            System.out.println("Global Leaderboard:");
            for (YoungStudent student : students) {
                System.out.println("Username: " + student.getStudentName() + ", Points: " + student.getCurrentPoints());
            }
        }
    }
    
    public static void main(String[] args) {
        YoungStudent.Leaderboard leaderboard = new YoungStudent.Leaderboard();

        // Adding some students to the leaderboard
        YoungStudent youngStudent1 = new YoungStudent("Alice");
        YoungStudent youngStudent2 = new YoungStudent("Bob");
        YoungStudent youngStudent3 = new YoungStudent("Charlie");
        YoungStudent youngStudent4 = new YoungStudent("Dave");

       /* leaderboard.addStudent(alice);
        leaderboard.addStudent(bob);
        leaderboard.addStudent(charlie);
        leaderboard.addStudent(dave);
        
        // Registering students for events
        alice.registerEvent(EventManager.m.);
        bob.registerEvent(event2);
        charlie.registerEvent(event2);
        dave.registerEvent(event3);
        alice.registerEvent(event3);  // Error, clash with another event on the same day */

        // Displaying the leaderboard
        leaderboard.displayLeaderboard();
    }
}
