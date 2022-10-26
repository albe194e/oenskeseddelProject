package com.example.oenskeliste.Repository;

import com.example.oenskeliste.Model.DCM;
import com.example.oenskeliste.Model.WishList;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishListRepository {

    Connection connection;

    public WishListRepository() {
        connection = DCM.getConnection();
    }

    public List<WishList> getAllWishlistByUserId(int id){
        List<WishList> wishList = new ArrayList<>();
        final String QUERY = "SELECT * FROM wishlist WHERE wishlist_owner_id=" + id;
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

    }

}
