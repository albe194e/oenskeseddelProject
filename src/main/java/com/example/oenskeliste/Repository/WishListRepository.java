package com.example.oenskeliste.Repository;

import com.example.oenskeliste.Model.DCM;
import com.example.oenskeliste.Model.Wish;
import com.example.oenskeliste.Model.WishList;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishListRepository {

    Connection connection = DCM.getConnection();
    //Use only when database is online

    public ArrayList<String> getWishesByPassword(String password){

        ArrayList<String> wishes = new ArrayList<>();

        String PASSWORD_QUERY = "SELECT * FROM user WHERE Password= " + password ;
        int userId = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(PASSWORD_QUERY);

            while (resultSet.next()){
                userId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String MAIN_QUERY = "SELECT * FROM wish WHERE UserId = " + userId;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(MAIN_QUERY);

            while (resultSet.next()){
                wishes.add(resultSet.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wishes;
    }

    public String getNameFromPassword(String password){
        String PASSWORD_QUERY = "SELECT * FROM user WHERE Password= " + password ;

        String name = "";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(PASSWORD_QUERY);

            while (resultSet.next()){
                name = resultSet.getString(2);
            }
        } catch (SQLException e) {
            System.out.println("Did not find name");
            throw new RuntimeException(e);
        }
        System.out.println("Name is:" + name);
        return name;
    }

    public List<WishList> getAllWishlistByUserId(int id) {
        List<WishList> wishList = new ArrayList<>();
        final String QUERY = "SELECT * FROM wish WHERE wishlist_owner_id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);

            while (resultSet.next()) {
                int wishlistId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                int ownerId = resultSet.getInt(4);


                wishList.add(new WishList(wishlistId, name, description, ownerId, true));
            }
            System.out.println("Wishlist is found");
        } catch (SQLException e) {
            System.out.println(e + "Not found");
            e.printStackTrace();
        }
        return wishList;
    }

    public List<WishList> createWishlist(WishList wishList) {
        List<WishList> newWishlist = new ArrayList<>();
        final String QUERY = "INSERT INTO wishlist (wishlist_name, wishlist_description, wishlist_owner_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, wishList.getName());
            preparedStatement.setString(2, wishList.getDescription());
            preparedStatement.setInt(3, wishList.getOwnerId());
            preparedStatement.executeUpdate();
            newWishlist.add(new WishList(wishList.getName(), wishList.getDescription(), wishList.getOwnerId()));
            preparedStatement.close();
            System.out.println("Wishlist created");
        } catch (SQLException e) {
            System.out.println(e + "Wishlist not created");
            e.printStackTrace();
        }
        return newWishlist;
    }

    public void deleteById(int id) {
        final String DELETE_QUERY = "DELETE FROM wishlist WHERE wishlist_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Deleted");
        } catch (SQLException e) {
            System.out.println("Not deleted");
            e.printStackTrace();
        }
    }


    public void updateById(WishList wishList) {
        final String UPDATE_QUERY = "UPDATE wishlist SET wishlist_name=?, wishlist_description=? WHERE wishlist_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, wishList.getName());
            preparedStatement.setString(2, wishList.getDescription());
            preparedStatement.setInt(3, wishList.getId());
        } catch (SQLException e) {
            System.out.println("Wishlist not updated");
            e.printStackTrace();
        }
    }



}
