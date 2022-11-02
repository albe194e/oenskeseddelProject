package com.example.oenskeliste.Repository;

import com.example.oenskeliste.Model.DCM;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
@Repository
public class WishListRepository {

    Connection connection = DCM.conn;

    public ArrayList<String> getWishesByPassword(String password) {

        //Finds the user by password
        ArrayList<String> wishes = new ArrayList<>();
        String PASSWORD_QUERY = "SELECT Id, Password from user";
        int userId = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(PASSWORD_QUERY);

            while (resultSet.next()) {

                String pw = resultSet.getString(2);

                if (pw.equals(password)) {
                    userId = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Selects wishes by user id
        String MAIN_QUERY = "SELECT * FROM wish WHERE UserId = " + userId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(MAIN_QUERY);

            while (resultSet.next()) {
                wishes.add(resultSet.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wishes;
    }
    public String getNameFromPassword(String password) {
        String PASSWORD_QUERY = "SELECT Name, Password FROM user";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(PASSWORD_QUERY);

            while (resultSet.next()) {
                String pw = resultSet.getString(2);

                if (pw.equals(password)) {
                    String name = resultSet.getString(1);
                    System.out.println("Name is:" + name);

                    return name;
                }
            }
        } catch (SQLException e) {
            System.out.println("Did not find name");
            throw new RuntimeException(e);
        }
        return null;
    }
}