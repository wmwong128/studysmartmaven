package com.studysmartjavafx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class parentChildDatabase {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "112233";

    private static final String INSERT_REQUEST_QUERY = "INSERT INTO parentchildrelationshiprequest (requester_id, receiver_id, status) VALUES (?, ?, ?)";
    private static final String UPDATE_REQUEST_STATUS_QUERY = "UPDATE parentchildrelationshiprequest SET status = ? WHERE (requester_id = ? AND receiver_id = ?) OR (requester_id = ? AND receiver_id = ?)";
    private static final String SELECT_PENDING_REQUESTS_QUERY = "SELECT * FROM parentchildrelationshiprequest WHERE receiver_id = ? AND status = 'PENDING'";
    private static final String SELECT_USER_ROLE_QUERY = "SELECT role FROM user WHERE id = ?";
    private static final String DELETE_REQUEST_QUERY = "DELETE FROM parentchildrelationshiprequest WHERE requester_id = ? AND receiver_id = ? AND status = 'PENDING'";
    private static final String JOIN_QUERY = "SELECT parentchildrelationship.parent_id, parentchildrelationship.child_id, booking.event_id, "
            + "event.title, event.description, event.date, event.time, event.educator_ID, event.end_date, "
            + "event.end_time, event.location_X, event.location_Y "
            + "FROM parentchildrelationship "
            + "LEFT JOIN booking ON parentchildrelationship.child_id = booking.user_id "
            + "LEFT JOIN event ON booking.event_id = event.id "
            + "WHERE parentchildrelationship.parent_id = ? AND parentchildrelationship.child_id = ?";

    public static void main(String[] args) throws IOException {
        try {
            Connection connection = (Connection) DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            // Example data
            
            ; // Child sends request to parent

//             Update request status
//            updateRequestStatus(connection, 11, 10, "CONFIRMED");
//            // Retrieve pending requests
//            rejectRequest(connection, 21,17);
//            retrievePendingRequests(connection, 17); // retrieve user parentchildrequest
retrieveAndWriteData(connection, 11, 10);
//            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void sendRelationshipRequest(Connection connection, int requesterId, int receiverId) throws SQLException {
        if (!isValidParentChildPair(connection, requesterId, receiverId)) {
            System.out.println("Invalid parent-child pair.");
            return;
        }
        String checkQuery = "SELECT COUNT(*) AS count FROM parentchildrelationshipREQUEST WHERE (requester_id = ? AND receiver_id = ?)OR (requester_id = ? AND receiver_id = ?)";
        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
        checkStatement.setInt(1, requesterId);
        checkStatement.setInt(2, receiverId);
        checkStatement.setInt(3, receiverId);
        checkStatement.setInt(4, requesterId);
        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt("count");

        // If no similar entry exists, insert the new friend relationship
        if (count == 0) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST_QUERY);
            preparedStatement.setInt(1, requesterId);
            preparedStatement.setInt(2, receiverId);
            preparedStatement.setString(3, "PENDING");
            preparedStatement.executeUpdate();
            System.out.println("Relationship request sent successfully.");
        } else {
            System.out.println("You have already sent the friend request. Please wait patiently");
        }
    }

    private static boolean isValidParentChildPair(Connection connection, int requesterId, int receiverId) throws SQLException {
        String requesterRole = getUserRole(connection, requesterId);
        String receiverRole = getUserRole(connection, receiverId);

        return (requesterRole.equals("PARENT") && receiverRole.equals("STUDENT"))
                || (requesterRole.equals("STUDENT") && receiverRole.equals("PARENT"));
    }

    private static String getUserRole(Connection connection, int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ROLE_QUERY);
        System.out.println(userId);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String role = resultSet.getString("role");
            return role;
        }
        return null;
    }

    private static void updateRequestStatus(Connection connection, int userId, int requestId, String status) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS_QUERY);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, userId);
        preparedStatement.setInt(3, requestId);
        preparedStatement.setInt(4, requestId);
        preparedStatement.setInt(5, userId);
        preparedStatement.executeUpdate();
        System.out.println("Relationship request status updated successfully.");
    }

    private static void retrievePendingRequests(Connection connection, int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PENDING_REQUESTS_QUERY);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("Pending Relationship Requests:");
        while (resultSet.next()) {
            int requesterId = resultSet.getInt("requester_id");
            String query = "SELECT * FROM user WHERE id = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(query);
            preparedStatement2.setInt(1, requesterId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet2.next()) {  // Move the cursor to the first row of resultSet2
                int id = resultSet2.getInt("id");
                String email = resultSet2.getString("email");
                String username = resultSet2.getString("username");
                Date lastSeen = resultSet2.getDate("last_logout");
                System.out.println("Request ID: " + id + ", Email: " + email + ", Username: " + username + ", Last Seen: " + lastSeen);
            }
        }
    }

    private static void rejectRequest(Connection connection, int requesterId, int receiverId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST_QUERY);
        preparedStatement.setInt(1, requesterId);
        preparedStatement.setInt(2, receiverId);
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Request from User ID: " + requesterId + " to User ID: " + receiverId + " has been successfully rejected.");
        } else {
            System.out.println("No pending request found from User ID: " + requesterId + " to User ID: " + receiverId + ".");
        }

        preparedStatement.close();
    }

    private static void retrieveAndWriteData(Connection connection, int userId, int childId) throws SQLException, IOException {

        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(JOIN_QUERY);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, childId);
        ResultSet rs = preparedStatement.executeQuery();
        ArrayList<String> data = new ArrayList<>();
        // Ensure the directory exists
        try (
                 BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\User\\Documents\\NetBeansProjects\\DatabaseProject\\src\\databaseproject\\parentViewChildEvent.txt"))) {

            // Get metadata to retrieve column names
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Write column names
            for (int i = 1; i <= columnCount; i++) {
                writer.write(rsmd.getColumnName(i));
                if (i < columnCount) {
                    writer.write(",");
                }
            }
            writer.newLine();

            // Write data rows
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    writer.write(rs.getString(i));
                    if (i < columnCount) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }

            System.out.println("Data has been written to output.csv");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
