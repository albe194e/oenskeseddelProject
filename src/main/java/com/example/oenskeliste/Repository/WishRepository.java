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
    Connection connection = DCM.getConn();

    //Use only when database is online

    public void addWish(String[] wishes, String name) {

        //Adds wishes to database
        final String ADD_WISH_QUERY = "INSERT INTO wish (UserId,Name ) VALUES(?, ?)";

        try {
            for (int i = 0; i < wishes.length; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_WISH_QUERY);

                preparedStatement.setInt(1, getUserId(name));
                preparedStatement.setString(2, wishes[i]);

                preparedStatement.executeUpdate();
            }
            System.out.println("Wish has been added");
        } catch (SQLException e) {
            System.out.println("Wish has not beed added");
            e.printStackTrace();
        }
    }
    private int getUserId(String username) {
        //Finds user id
        String QUARY = "SELECT Id, Name from user";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUARY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                if (name.equals(username)) {
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
    public String getPassword(String name){
        String QUARY = "SELECT Name, Password from user";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUARY);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);

                if (name.equals(username)){
                    return password;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
