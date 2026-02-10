package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import Library.Users.User;
import db.DBConection;

public class UserDAO {
    public void saveUser(User user) throws ClassNotFoundException , SQLException{
        String sql = "INSERT INTO users (id, name, email, type) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            // Si tienes el objeto ContactData que mencionaste antes:
            pstmt.setString(3, user.getContact().getEmail());
            pstmt.setString(4, user.getClass().getSimpleName());
            
            pstmt.executeUpdate();
            System.out.println("User registered in DB: " + user.getName());
            
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }
    
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
    List<User> users = new ArrayList<>();
    String sql = "SELECT * FROM users";

    // Usamos el bloque try-with-resources para manejar el cierre automático
    try (Connection conn = DBConection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        System.out.println("\n--- CONTENIDO DE LA TABLA USERS ---");
        while (rs.next()) {
            String name = rs.getString("name");
            String id = rs.getString("id");
            String type = rs.getString("type");
            System.out.println("ID: " + id + " | Nombre: " + name + " | Tipo: " + type);
            
            // Aquí podrías reconstruir el objeto si lo necesitas
            // users.add(new Reader(id, name, ...));
        }
    } catch (SQLException e) {
        System.err.println("Error al consultar usuarios: " + e.getMessage());
        throw e;
    }
    return users;
}
    public void deleteAllUsers() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM users";
        try (Connection conn = DBConection.getConnection(); // Usa tu método de conexión
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }
}
