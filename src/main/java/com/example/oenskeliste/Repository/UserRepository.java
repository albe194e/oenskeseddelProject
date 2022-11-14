package com.example.oenskeliste.Repository;
import com.example.oenskeliste.Model.DCM;
import com.example.oenskeliste.Model.User;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class UserRepository {

    Connection connection = DCM.getConn();

    //Use only when database is online
    public void createUser(User user) {
        //Creates User
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
    public void deleteUser(String user){

        //Finds UserId
        String QUARY_GETID = "SELECT Id, Name from user";
        int id = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUARY_GETID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                String name = resultSet.getString(2);

                if (name.equals(user)){
                    id = resultSet.getInt(1);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Deletes all wishes linked to user
        String QUARY_DELETE_WISHES = "DELETE FROM wish WHERE UserId=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUARY_DELETE_WISHES);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Deletes the user
        String QUARY = "DELETE from user where Name=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUARY);
            preparedStatement.setString(1,user);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkIfUserExist(User user) {
        //Selects user if existing
        String QUARY = "SELECT Name, Email from user";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUARY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String email = resultSet.getString(2);

                if (name.equals(user.getName()) && email.equals(user.getEmail())) {
                    System.out.println("User already exists");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getPassword(User user) {

        String QUARY = "SELECT Name, Password FROM user";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUARY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);

                if (user.getName().equals(name)) {
                    return resultSet.getString(2);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
