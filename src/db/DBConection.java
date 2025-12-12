package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to manage database connections to the library system.
 * Provides a method to obtain a connection to the MySQL database.
 *
 * This class uses the JDBC driver "com.mysql.cj.jdbc.Driver" and connects
 * to the database "biblioteca" on localhost.
 */
public class DBConection {

    private static final String USER = "root";
    private static final String PASS = "password";

    /**
     * Obtains a new connection to the MySQL database.
     *
     * The method loads the MySQL JDBC driver and establishes a connection
     * using the configured URL, username, and password.
     *
     * @return a {@link Connection} object connected to the database
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the MySQL JDBC driver class is not found
     * @throws RuntimeException if the driver cannot be loaded or the connection cannot be established
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/biblioteca?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
            return DriverManager.getConnection(url, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver de MySQL", e);
        }
    }
}
