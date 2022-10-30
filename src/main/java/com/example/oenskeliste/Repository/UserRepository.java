package com.example.oenskeliste.Repository;

import com.example.oenskeliste.Model.DCM;
import com.example.oenskeliste.Model.User;
import org.springframework.stereotype.Repository;


import java.sql.*;

@Repository
public class UserRepository {

    Connection connection = DCM.getConnection();

    //Use only when database is online


    public void createUser(User user) {
        String query = "INSERT INTO user (Name, Email, Password) VALUES(?, ?, ?)";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
                String userName = resultSet.getString(4);
                selectedUser = new User(userId, userEmail, userName, userPassword);
            }
        } catch (SQLException e) {
            System.out.println("Can't find user!");
            e.printStackTrace();
        }

        return selectedUser;
    }


}
