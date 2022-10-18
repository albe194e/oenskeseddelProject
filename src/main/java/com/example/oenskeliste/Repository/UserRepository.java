package com.example.oenskeliste.Repository;

import com.example.oenskeliste.Model.DCM;
import com.example.oenskeliste.Model.User;


import java.sql.*;

public class UserRepository {

    Connection connection;

    public void UserRepository() {
        connection = DCM.getConnection();
    }

    public void createUser(User user) {
        String query = "INSERT INTO user (user_email, user_password, user_firstname, user_lastname) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println("Can't insert user");
            e.printStackTrace();
        }
    }


    public User selectUserByMail(String email) {
        String query = "SELECT * FROM user WHERE user_email = '" + email + "'";
        User selectedUser = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String userEmail = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                String userFirstName = resultSet.getString(4);
                String userLastName = resultSet.getString(5);
                selectedUser = new User(userId, userEmail, userFirstName, userLastName, userPassword);
            }
        } catch (SQLException e) {
            System.out.println("Can't find user!");
            e.printStackTrace();
        }

        return selectedUser;
    }


}
