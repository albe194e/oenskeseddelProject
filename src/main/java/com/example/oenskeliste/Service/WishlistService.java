package com.example.oenskeliste.Service;

import com.example.oenskeliste.Model.Wish;
import com.example.oenskeliste.Model.WishList;
import com.example.oenskeliste.Repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class WishlistService {

    private WishListRepository wishListRepository;

    public WishlistService(WishListRepository wishListRepository) {

        this.wishListRepository = wishListRepository;
    }

    public ArrayList<String> getAllByPassword(WebRequest req) {

        return wishListRepository.getWishesByPassword(req.getParameter("password"));
    }

    public String getName(WebRequest req){
        return wishListRepository.getNameFromPassword(req.getParameter("password"));
    }


}
