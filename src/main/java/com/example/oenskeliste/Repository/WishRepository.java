package com.example.oenskeliste.Repository;


import com.example.oenskeliste.Model.DCM;
import com.example.oenskeliste.Model.Wish;
import com.example.oenskeliste.Service.UserService;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

                preparedStatement.setInt(1,getUserId());
                preparedStatement.setString(2,wishes[i]);

                preparedStatement.executeUpdate();
            }

            System.out.println("Wish has been added");
        } catch (SQLException e) {
            System.out.println("Wish has not beed added");
            e.printStackTrace();
        }
    }
    public int getUserId(){

        String QUARY = "SELECT Id, Name from user";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUARY);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                if (name.equals(UserService.currentUser.getName())){
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


    public void deleteWish(int id) {
        final String DELETE_QUERY = "DELETE FROM wish WHERE wish_id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Wish is deleted");
        } catch (SQLException e) {
            System.out.println("Wish not deleted");
            e.printStackTrace();
        }
    }

    public List<Wish> findListById(int id) {
        List<Wish> wishList = new ArrayList<>();
        final String FIND_QUERY = "SELECT * FROM wish WHERE wishlist_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int wishId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                String link = resultSet.getString(4);
                int isReservedResult = resultSet.getInt(6);

                boolean isReserved = true;
                if (isReservedResult == 0) {
                    isReserved = false;
                }

                wishList.add(new Wish(wishId, name, description, link, isReservedResult));
            }



        } catch (SQLException e) {
            System.out.println("Could not find list");
            e.printStackTrace();
        }
        return wishList;
    }

    public void updateWish(Wish wish) {
        final String UPDATE_QUERY = "UPDATE wish SET wish_name=?, wish_description=?, wish_link=? WHERE wish_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, wish.getName());
            preparedStatement.setString(2, wish.getDescription());
            preparedStatement.setString(3, wish.getLink());
            preparedStatement.setInt(4, wish.getId());
            preparedStatement.executeUpdate();
            System.out.println("List has been updated");
        } catch (SQLException e) {
            System.out.println("Not updated");
            e.printStackTrace();
        }
    }

}
