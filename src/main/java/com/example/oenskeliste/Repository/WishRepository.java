package com.example.oenskeliste.Repository;


import com.example.oenskeliste.Model.DCM;
import com.example.oenskeliste.Service.UserService;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WishRepository {
    Connection connection = DCM.conn;
    //Use only when database is online

    public void addWish(String[] wishes) {
        //Adds wishes to database
        final String ADD_WISH_QUERY = "INSERT INTO wish (UserId,Name ) VALUES(?, ?)";

        try {
            for (int i = 0; i < wishes.length; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_WISH_QUERY);

                preparedStatement.setInt(1, getUserId());
                preparedStatement.setString(2, wishes[i]);

                preparedStatement.executeUpdate();
            }
            System.out.println("Wish has been added");
        } catch (SQLException e) {
            System.out.println("Wish has not beed added");
            e.printStackTrace();
        }
    }
    private int getUserId() {
        //Finds user id
        String QUARY = "SELECT Id, Name from user";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUARY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                if (name.equals(UserService.currentUser.getName())) {
                    System.out.println("ID: " + id +
                            "\nName: " + name);
                    return id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.err.println("Didnt work");
        return 0;
    }
}
