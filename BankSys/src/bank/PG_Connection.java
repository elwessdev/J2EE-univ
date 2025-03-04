package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PG_Connection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bank";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}