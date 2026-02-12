package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import Library.resources.Resource;
import Library.services.FactoryResource;
import db.DBConection;

/**
 * Data Access Object (DAO) for managing {@link Resource} persistence.
 *
 * This class provides CRUD operations for library resources, handling the 
 * mapping between the Java object model and the relational database schema.
 */
public class ResourceDAO {

    /**
     * Persists a new resource into the database.
     * 
     * The method determines the resource type (e.g., Book, Magazine) using the 
     * class's simple name and stores it in the 'type' column.
     *
     * @param res The {@link Resource} object to be saved.
     * @throws ClassNotFoundException If the database driver is not found.
     */
    public void saveResource(Resource res) throws ClassNotFoundException {
        String sql = "INSERT INTO resources (id, title, author, type) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, res.getId());
            pstmt.setString(2, res.getName());
            pstmt.setString(3, res.getAuthor());
            // Determine if it is a Book or Magazine for the 'type' column
            pstmt.setString(4, res.getClass().getSimpleName());
            
            pstmt.executeUpdate();
            System.out.println("Resource saved to DB: " + res.getName());
            
        } catch (SQLException e) {
            System.err.println("Error saving resource: " + e.getMessage());
        }
    }

    /**
     * Retrieves all resources currently stored in the database.
     *
     * Note: Currently, this method prints the content to the console. 
     * The returned list is initialized but not yet populated with objects.
     *
     * @return A {@link List} of {@link Resource} objects retrieved from the database.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the database driver is not found.
     */
    public List<Resource> getAllResources() throws SQLException, ClassNotFoundException {
        List<Resource> resources = new ArrayList<>();
        String sql = "SELECT * FROM resources";

        // Try-with-resources ensures the connection is closed automatically
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- RESOURCES TABLE CONTENT ---");
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String type = rs.getString("type");

                System.out.println("[" + type.toUpperCase() + "] ID: " + id + 
                                   " | Title: " + title + 
                                   " | Author: " + author);
                resources.add(FactoryResource.createResource(type, title, author, id));
            }
           
        } catch (SQLException e) {
            System.err.println("Error querying resources: " + e.getMessage());
            throw e;
        }
        return resources;
    }

    /**
     * Deletes all records from the resources table.
     *
     * Use with caution as this operation wipes the entire table and 
     * cannot be rolled back.
     *
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the database driver is not found.
     */
    public void deleteAllResources() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM resources";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }
}