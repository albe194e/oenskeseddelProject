package com.example.oenskeliste.Repository;


import com.example.oenskeliste.Model.DCM;
import com.example.oenskeliste.Model.Wish;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class WishRepository {

    Connection connection;

    //Use only when database is online
    /*public WishRepository() {
        connection = DCM.getConnection();
    }*/

    public void addWish(Wish wish) {
        final String ADD_WISH_QUERY = "INSERT INTO wish(wish_name, wish_description, " +
                "wish_price, wish_link, wishlist_id) VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_WISH_QUERY);
            preparedStatement.setString(1, wish.getName());
            preparedStatement.setString(2, wish.getDescription());
            preparedStatement.setDouble(3, wish.getPrice());
            preparedStatement.setString(4, wish.getLink());
            preparedStatement.setInt(5, wish.getWishlistId());
            preparedStatement.executeUpdate();
            System.out.println("Wish has been added");
        } catch (SQLException e) {
            System.out.println("Wish has not beed added");
            e.printStackTrace();
        }
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

}
