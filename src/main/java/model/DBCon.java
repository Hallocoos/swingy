package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBCon {
    private final String url = "jdbc:postgresql://localhost:5432/swingy";
    private final String user = "postgres";
    private final String password = "12345678";
    Scanner scanner = new Scanner(System.in);

    public Connection connect() {
        Connection conn = null;
        try {
            // Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null)
                System.out.println("Connected to the PostgreSQL server successfully.");
            else
                System.out.println("Failed to make connection!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String classpath = System.getProperty("java.class.path");
        System.out.println(classpath);
        String line = scanner.nextLine();
        return conn;
    }
}
