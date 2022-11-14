package com.example.oenskeliste.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DCM {
    private static String hostname;
    private static String username;
    private static String password;
    private static Connection conn = createConnection();

    private static Connection createConnection() {

        File file = new File("src/main/resources/static/databaseLogin.csv");

        ArrayList<String> login = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()){
                login.add(fileScanner.next());
            }

            hostname = login.get(0);
            username = login.get(1);
            password = login.get(2);

            login.clear();

        } catch (FileNotFoundException e){

        }

        try {
            conn = DriverManager.getConnection(hostname, username, password);
        } catch (SQLException e) {
            System.out.println("TASK FAILED, THROWING EXCEPTION");
            throw new RuntimeException();
        }
        System.out.println("CREATING CONNECTION");
        return conn;
    }

    public static Connection getConn() {
        return conn;
    }
}