package com.studysmartjavafx;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */

public class FriendRequestDatabase {

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "112233";

    // SQL statements
    private static final String INSERT_QUERY = "INSERT INTO user_friend (user_id1, user_id2, status) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM user_friend";

    public static void main(String[] args) {
        try {
            // Establish connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Insert data
            // Example data

            // Retrieve data
        
            retrieveMyFriends(connection, 5);
            rejectFriendRequest(connection, 3,5);
            // Close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert friend relationship into the database
    private static void insertFriend(Connection connection, int userId1, int userId2, String status) throws SQLException {
    // Ensure userId1 is smaller than userId2
    if (userId1 > userId2) {
        int temp = userId2;
        userId2 = userId1;
        userId1 = temp;
        status = "REQ_UID2";
    }

    // Check if a similar entry already exists
    String checkQuery = "SELECT COUNT(*) AS count FROM user_friend WHERE user_id1 = ? AND user_id2 = ?";
    PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
    checkStatement.setInt(1, userId1);
    checkStatement.setInt(2, userId2);
    ResultSet resultSet = checkStatement.executeQuery();
    resultSet.next();
    int count = resultSet.getInt("count");

    // If no similar entry exists, insert the new friend relationship
    if (count == 0) {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        preparedStatement.setInt(1, userId1);
        preparedStatement.setInt(2, userId2);
        preparedStatement.setString(3, status);
        preparedStatement.executeUpdate();
        System.out.println("Friend relationship inserted successfully.");
    } else {
        System.out.println("Similar entry already exists. Skipping insertion.");
    }
}

   private static void updateFriend(Connection connection , int userId1, int userId2, String status) throws SQLException {
        String updateQuery = "UPDATE user_friend SET status = ? WHERE (user_id1 = ? AND user_id2 = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString (1,status);
        preparedStatement.setInt (2,userId1);
        preparedStatement.setInt (3,userId2);
        preparedStatement.executeUpdate();
        System.out.println("Status Updated");
    }

    // Method to retrieve friend relationships from the database
    private static void retrieveFriends(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_QUERY);

        // Print retrieved friend relationships
        System.out.println("Retrieved Friend Relationships:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int userId1 = resultSet.getInt("user_id1");
            int userId2 = resultSet.getInt("user_id2");
            String status = resultSet.getString("status");
            System.out.println("ID: " + id + ", User ID 1: " + userId1 + ", User ID 2: " + userId2 + ", Status: " + status);
        }
    }
        private static void retrieveMyFriends(Connection connection, int userId) throws SQLException {
            String SELECT_FRIENDS_QUERY = "SELECT * FROM user_friend WHERE (user_id1 = ? AND status = 'FRIEND') OR (user_id2 = ? AND status = 'FRIEND')";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FRIENDS_QUERY);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Print retrieved friend relationships
        System.out.println("Retrieved Friends for User " + userId + ":");
        while (resultSet.next()) {
            int friendId1 = resultSet.getInt("user_id1");
            int friendId2 = resultSet.getInt("user_id2");
            System.out.println("Friendship between User " + friendId1 + " and User " + friendId2);
        }
    }
           private static void rejectFriendRequest(Connection connection, int userId1, int userId2) throws SQLException {
                   if (userId1 > userId2) {
        int temp = userId2;
        userId2 = userId1;
        userId1 = temp;
    }
               String REJECT_FRIEND_REQUEST_QUERY = "DELETE FROM user_friend WHERE (user_id1 = ? AND user_id2 = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(REJECT_FRIEND_REQUEST_QUERY);
        preparedStatement.setInt(1, userId1);
        preparedStatement.setInt(2, userId2);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Friend request rejected successfully.");
        } else {
            System.out.println("No pending friend request found to reject.");
        }
    }
}

    

