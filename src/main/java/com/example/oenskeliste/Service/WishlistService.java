package com.example.oenskeliste.Service;

import com.example.oenskeliste.Model.WishList;
import com.example.oenskeliste.Repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private WishListRepository wishListRepository;

    public WishlistService(WishListRepository wishListRepository) {

        this.wishListRepository = wishListRepository;
    }


}
