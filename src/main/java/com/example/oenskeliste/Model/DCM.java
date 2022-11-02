package com.example.oenskeliste.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DCM {
    private static String hostname;
    private static String username;
    private static String password;
    public static Connection conn = getConnection();

    private static Connection getConnection() {
        hostname = "jdbc:mysql://nocluewhatsoever.mysql.database.azure.com/wishlist";
        username = "HaDoCK";
        password = "Nikolaj#14";

        try {
            conn = DriverManager.getConnection(hostname, username, password);
        } catch (SQLException e) {
            System.out.println("TASK FAILED, THROWING EXCEPTION");
            throw new RuntimeException();
        }
        System.out.println("CREATING CONNECTION");
        return conn;
    }
}
