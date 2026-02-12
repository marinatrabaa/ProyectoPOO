package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import Library.Users.Reader;
import Library.Users.User;
import Library.Users.User.ContactData;
import db.DBConection;

/**
 * Data Access Object (DAO) for managing {@link User} persistence.
 *
 * This class handles all database operations related to library users, including
 * registration, retrieval, and batch deletion.
 */
public class UserDAO {

    /**
     * Saves a new user to the database.
     *
     * This method extracts user details including contact information and uses
     * the class type (e.g., Student, Teacher, Reader) to populate the 'type' column.
     *
     * @param user The {@link User} object containing the data to be persisted.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If a database access error occurs.
     */
    public void saveUser(User user) throws ClassNotFoundException , SQLException{
        String sql = "INSERT INTO users (id, name, email, type) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            // Using the ContactData object within the User:
            pstmt.setString(3, user.getContact().getEmail());
            pstmt.setString(4, user.getClass().getSimpleName());
            
            pstmt.executeUpdate();
            System.out.println("User registered in DB: " + user.getName());
            
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves all users currently stored in the database.
     * Currently, this method logs the user details to the standard output.
     * To fully implement this, the {@code users} list should be populated 
     * with instantiated User objects.
     *
     * @return A {@link List} of {@link User} objects retrieved from the database.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     */
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        // Try-with-resources handles automatic resource closing
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- USERS TABLE CONTENT ---");
            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                String type = rs.getString("type");
                ContactData data = new ContactData(id, type);
                System.out.println("ID: " + id + " | Name: " + name + " | Type: " + type);

                users.add(new Reader(id, name, data));
            }
        } catch (SQLException e) {
            System.err.println("Error querying users: " + e.getMessage());
            throw e;
        }
        return users;
    }

    /**
     * Permanently deletes all users from the database.
     *
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     */
    public void deleteAllUsers() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM users";
        try (Connection conn = DBConection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }
}