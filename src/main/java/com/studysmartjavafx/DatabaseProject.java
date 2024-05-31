package com.studysmartjavafx;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProject {

    static Connection con;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseProject pro = new DatabaseProject();

        pro.createConnection(); // Call createConnection first
      //  insertEvent(); // Then call insertData
        viewEvent();
        //parentStudentRelationship(4, 7);
       // bookEvent(7, 7);
       //displayNonChildEvent(4);
      // List<String> children = getParentForChildren(6);
       // System.out.println(children);
    }

    void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "112233");
    }

    static void insertData() throws SQLException {
        Random r = new Random();
        String email = "student2@gmail.com";
        String first_name = "student";
        String last_name = "baba";
        String age = "12";
        String username = "usernamefather";
        String password = "77445";
        String role = "Student";
        double location_X = r.nextDouble(1000) - 500;
        double location_Y = r.nextDouble(1000) - 500;

        Statement stmt = con.createStatement();
        String dbop = "INSERT INTO USER  (email, first_name, last_name, age, username, password, role, location_X, location_Y) VALUES('" + email + "','" + first_name + "','" + last_name + "','" + age + "','" + username + "','" + password + "','" + role + "','" + location_X + "','" + location_Y + "');";
        stmt.execute(dbop);
        stmt.close();
    }

    static void insertEvent() throws SQLException {
        String email = "Pusat Sains & Kreativiti Terengganu";
        String last_name = "Petrosains Science Discovery Centre";
        String age = "2024-05-04";
        String username = "14:00:00";
        String password = "1";
        String role = "2024-05-04";
        String location = "15:00:00";
        double location_X = 263.99;
        double location_Y = -57.31;

        Statement stmt = con.createStatement();
        String dbop = "INSERT INTO EVENT (title, description,  date , time , educator_ID,end_date, end_time, location_X, location_Y) VALUES('" + email + "','" + last_name + "','" + age + "','" + username + "','" + password + "','" + role + "','" + location + "','" + location_X + "','" + location_Y + "');";
        stmt.execute(dbop);
        stmt.close();
    }

    static void viewEvent() throws SQLException {
        String today = "2024-05-03";
        String query = "SELECT * FROM EVENT WHERE date >='" + today + "' ORDER BY date , time LIMIT 3";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            String venue = rs.getString(4);
            String date = rs.getString(5);
            System.out.println("Venue:" + venue + " Date: " + date);
        }
        stm.execute(query);
        stm.close();
    }

    static void parentStudentRelationship(int parentId, int childId) throws SQLException {
        Statement stmt = con.createStatement();
        String dbop = "INSERT INTO ParentChildRelationship (parent_id, child_id) VALUES(" + parentId + ", " + childId + ")";
        stmt.execute(dbop);
        stmt.close();
    }

 static void bookEvent(int userId, int eventId) {
    try {
        // Check if the event exists
        String eventQuery = "SELECT * FROM event WHERE id = ?";
        PreparedStatement eventStmt = con.prepareStatement(eventQuery);
        eventStmt.setInt(1, eventId);
        ResultSet eventRs = eventStmt.executeQuery();
        
        if (eventRs.next()) {
            // Event exists, proceed to check for clashes
            String eventDate = eventRs.getString("date");
            // Prepare clashes query
            String clashesQuery = "SELECT * FROM booking WHERE user_id = ? AND event_id IN (SELECT id FROM event WHERE date = ?)";
            PreparedStatement clashesStmt = con.prepareStatement(clashesQuery);
            clashesStmt.setInt(1, userId);
            clashesStmt.setString(2, eventDate);
            ResultSet clashesRs = clashesStmt.executeQuery();
            
            if (clashesRs.next()) {
                // Clashes found, handle accordingly
                System.out.println("Clashes found. Cannot book the event.");
            } else {
                // No clashes found, proceed to book the event
                // Insert booking into database
                String insertQuery = "INSERT INTO booking (user_id, event_id) VALUES (?, ?)";
                PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, eventId);
                insertStmt.executeUpdate();
                System.out.println("Event booked successfully!");
            }
        } else {
            // Event does not exist
            System.out.println("Event not found. Cannot book the event.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    static boolean hasClashes(int userID, int eventID) {
        try {
            String clashesQuery = "SELECT * FROM booking b JOIN event e ON b.event_id = e.id "
                    +" WHERE b.user_id = ? AND e.event_ID = ?)";
            PreparedStatement clashesStmt = con.prepareStatement(clashesQuery);
            clashesStmt.setInt(1, userID);
            clashesStmt.setInt(2, eventID);
            ResultSet clashesRs = clashesStmt.executeQuery();

            return clashesRs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    // Got Problem
static void displayNonChildEvent(int parentID){
    String query = "SELECT p.child_id, e.id AS event_id, e.title, e.date, e.time, e.end_date, e.end_time, e.location_X, e.location_Y " +
                   "FROM ParentChildRelationship p " +
                   "JOIN event e ON p.child_id = e.child_id " +
                   "WHERE p.parent_id = ?;";
            
    try(PreparedStatement pstmt = con.prepareStatement(query)){
        pstmt.setInt(1, parentID);
                  
        try(ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                int childID = rs.getInt("child_id");
                int eventID = rs.getInt("event_id");
                String title = rs.getString("title");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String end_date = rs.getString("end_date");
                String end_time = rs.getString("end_time");
                String location_X = rs.getString("location_X");
                String location_Y = rs.getString("location_Y");
                          
              //  if(!hasClashes(childID, eventID)){
                    System.out.println("Title: " + title);
                    System.out.println("Venue: " + location_X + ", " + location_Y);
                    System.out.println();
              //  }
            }
        }
    } catch (SQLException ex) {      
        Logger.getLogger(DatabaseProject.class.getName()).log(Level.SEVERE, null, ex);
    }      
}
static List<String> getChildrenForParent(int parentID){
     List<String> children = new ArrayList<>();
        String query = "SELECT * FROM ParentChildRelationship p "
                     + "JOIN USER u ON p.child_id = u.id "
                     + "WHERE p.parent_ID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, parentID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String childUsername = rs.getString("username");
                children.add(childUsername);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return children;
    }
       static List<String> getParentForChildren(int childID){
     List<String> parent = new ArrayList<>();
        String query = "SELECT * FROM ParentChildRelationship p "
                     + "JOIN USER u ON p.parent_id = u.id "
                     + "WHERE p.child_ID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, childID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String parentUsername = rs.getString("username");
                parent.add(parentUsername);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parent;
    }
       
    }

