package dao;

import Library.lends.Lend;
import Library.resources.Resource;
import db.DBConection;
import Library.Users.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Data Access Object (DAO) for managing {@link Lend} entities in the database.
 * Provides methods to add new lends, mark lends as returned, and retrieve
 * all lends. Uses lists of {@link User} and {@link Resource} objects in memory
 * to reconstruct the relationships.
 */
public class LendDAO{

    private List<User> users;
    private List<Resource> resources;

    public LendDAO(List<User> users, List<Resource> resources) {
        this.users = users;
        this.resources = resources;
    }

    /**
     * Adds lend to a data base
     * 
     * @param lend
     * @throws SQLException
     */
    public void addLend(Lend lend) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO lends (resource_id, user_id, start_date, finish_date, returned) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DBConection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, lend.getResource().getId());
        stmt.setString(2, lend.getUser().getId());
        stmt.setDate(3, Date.valueOf(lend.getStartDate()));
        stmt.setDate(4, Date.valueOf(lend.getFinishDate()));
        stmt.setBoolean(5, lend.isReturned());

        stmt.executeUpdate(); 

        try (ResultSet gk = stmt.getGeneratedKeys()) {
            if (gk.next()) {
                int generatedId = gk.getInt(1);
                lend.setId(generatedId);
            }
        }
    }
}

    /**
     *Updates return state at database and object
     * 
     * @param lend
     * @throws SQLException
     */
    public void markAsReturned(Lend lend) throws SQLException, ClassNotFoundException {
        // 1. Tries to update by id if extists
        if (lend.getId() > 0) {
            String sql = "UPDATE lends SET returned = true WHERE id = ?";
            try (Connection conn = DBConection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, lend.getId());
                int rowsUpdated = stmt.executeUpdate();
                System.out.println("DEBUG: markAsReturned - by id, ID: " + lend.getId() + ", Rows updated: " + rowsUpdated);
            } catch(SQLException e){
                System.err.println("Error retrieving lends: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            // Fallback: updates latest lend for that user+resource
            String sql = "UPDATE lends SET returned = true WHERE resource_id = ? AND user_id = ? AND returned = 0 ORDER BY start_date DESC LIMIT 1";
            try (Connection conn = DBConection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, lend.getResource().getId());
                stmt.setString(2, lend.getUser().getId());
                //int rowsUpdated = stmt.executeUpdate();
                //System.out.println("DEBUG: markAsReturned - fallback by user+resource, Rows updated: " + rowsUpdated);
            } catch(SQLException e){
                System.err.println("Error retrieving lends: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // 2. Updates object at memory
        lend.setReturned(true);
    }

    /**
     * Obtains all lends at database and remakes objects using lists at memory
     * 
     * @return all lends registered at database
     * @throws SQLException
     */
    public List<Lend> getAllLends() throws SQLException, ClassNotFoundException {
        List<Lend> lends = new ArrayList<>();
        String sql = "SELECT * FROM lends";

        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String resourceId = rs.getString("resource_id");

                User user = users.stream()
                        .filter(u -> u.getId().equals(userId))
                        .findFirst()
                        .orElse(null);

                Resource resource = resources.stream()
                        .filter(r -> r.getId().equals(resourceId))
                        .findFirst()
                        .orElse(null);

                if (user == null || resource == null) continue; // ignore if not found

                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate finishDate = rs.getDate("finish_date").toLocalDate();
                boolean returned = rs.getBoolean("returned");

                Lend lend = new Lend(resource, user, startDate, finishDate);
                lend.setId(rs.getInt("id")); // asing id from db
                lend.setReturned(returned); // asing directly the state from database

                lends.add(lend);
            }
        } catch (SQLException e){
            System.err.println("Error retrieving lends: " + e.getMessage());
            e.printStackTrace();
        }

        return lends;
    }

    public void deleteAllLends() throws SQLException, ClassNotFoundException {
    String sql = "DELETE FROM lends";
    try (Connection conn = DBConection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.executeUpdate();
    }
}
}
