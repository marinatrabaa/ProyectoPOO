package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import Library.resources.Resource;
import db.DBConection;

public class ResourceDAO {
    public void saveResource(Resource res) throws ClassNotFoundException {
        String sql = "INSERT INTO resources (id, title, author, type) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, res.getId());
            pstmt.setString(2, res.getName());
            pstmt.setString(3, res.getAuthor());
            // Determinamos si es Book o Magazine para la columna 'type'
            pstmt.setString(4, res.getClass().getSimpleName());
            
            pstmt.executeUpdate();
            System.out.println("Resource saved to DB: " + res.getName());
            
        } catch (SQLException e) {
            System.err.println("Error saving resource: " + e.getMessage());
        }
    }

    public List<Resource> getAllResources() throws SQLException, ClassNotFoundException {
    List<Resource> resources = new ArrayList<>();
    String sql = "SELECT * FROM resources";

    // Try-with-resources para asegurar el cierre de la conexión
    try (Connection conn = DBConection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        System.out.println("\n--- CONTENIDO DE LA TABLA RESOURCES ---");
        while (rs.next()) {
            String id = rs.getString("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String type = rs.getString("type");

            System.out.println("[" + type.toUpperCase() + "] ID: " + id + 
                               " | Título: " + title + 
                               " | Autor: " + author);
            
        }
    } catch (SQLException e) {
        System.err.println("Error al consultar recursos: " + e.getMessage());
        throw e;
    }
    return resources;
}

    public void deleteAllResources() throws SQLException, ClassNotFoundException {
    String sql = "DELETE FROM resources";
    try (Connection conn = DBConection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.executeUpdate();
    }
}

}