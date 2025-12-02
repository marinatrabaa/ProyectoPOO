package dao;

import Library.lends.Lend;
import Library.resources.Resource;
import db.DBConection;
import Library.Users.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LendDAO {

    private List<User> users;
    private List<Resource> resources;

    public LendDAO(List<User> users, List<Resource> resources) {
        this.users = users;
        this.resources = resources;
    }

    // Agrega un préstamo a la base de datos
    public void addLend(Lend lend) throws SQLException {
        String sql = "INSERT INTO lends (user_id, resource_id, start_date, finish_date, returned) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lend.getUser().getId());
            stmt.setString(2, lend.getResource().getId());
            stmt.setDate(3, Date.valueOf(lend.getStartDate()));
            stmt.setDate(4, Date.valueOf(lend.getFinishDate()));
            stmt.setBoolean(5, lend.isReturned());

            stmt.executeUpdate();
        }
    }

    // Obtiene todos los préstamos de la base de datos y reconstruye objetos usando listas en memoria
    public List<Lend> getAllLends() throws SQLException {
        List<Lend> lends = new ArrayList<>();
        String sql = "SELECT * FROM lends";

        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String resourceId = rs.getString("resource_id");

                // Buscar usuario y recurso en las listas existentes
                User user = users.stream()
                        .filter(u -> u.getId().equals(userId))
                        .findFirst()
                        .orElse(null);

                Resource resource = resources.stream()
                        .filter(r -> r.getId().equals(resourceId))
                        .findFirst()
                        .orElse(null);

                if (user == null || resource == null) continue; // ignorar si no se encuentra

                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate finishDate = rs.getDate("finish_date").toLocalDate();

                // Usar el nuevo constructor que acepta fechas y estado
                Lend lend = new Lend(resource, user, startDate, finishDate);

                lends.add(lend);
            }
        }

        return lends;
    }

    // Actualiza el estado de devolución
    public void markAsReturned(Lend lend) throws SQLException {
        String sql = "UPDATE lends SET returned = ? WHERE user_id = ? AND resource_id = ? AND start_date = ?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, lend.isReturned());
            stmt.setString(2, lend.getUser().getId());
            stmt.setString(3, lend.getResource().getId());
            stmt.setDate(4, Date.valueOf(lend.getStartDate()));

            stmt.executeUpdate();
        }
    }
}
