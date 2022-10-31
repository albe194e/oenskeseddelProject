package com.example.oenskeliste.Repository;

import com.example.oenskeliste.Model.DCM;
import com.example.oenskeliste.Model.User;
import org.springframework.stereotype.Repository;


import java.sql.*;

@Repository
public class UserRepository {

    Connection connection = DCM.conn;

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

    public boolean checkIfUserExist(User user){

        try {
            String QUARY = "SELECT Name, Email from user";
            PreparedStatement preparedStatement = connection.prepareStatement(QUARY);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString(1);
                String email = resultSet.getString(2);

                if (name.equals(user.getName()) && email.equals(user.getEmail())){
                    System.out.println("User already exists");
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public User selectUserByMail(String email) {
        String query = "SELECT * FROM user WHERE user_email = '" + email + "'";
        User selectedUser = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String userEmail = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                String userName = resultSet.getString(4);
                selectedUser = new User(userEmail, userName, userPassword);
            }
        } catch (SQLException e) {
            System.out.println("Can't find user!");
            e.printStackTrace();
        }

        return selectedUser;
    }




}
