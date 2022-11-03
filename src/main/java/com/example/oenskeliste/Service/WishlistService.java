package com.example.oenskeliste.Service;
import com.example.oenskeliste.Repository.WishListRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;

@Service
public class WishlistService {

    private WishListRepository wishListRepository = new WishListRepository();

    public ArrayList<String> getAllByPassword(WebRequest req) {

        return wishListRepository.getWishesByPassword(req.getParameter("password"));
    }

    public String getName(WebRequest req){
        return wishListRepository.getNameFromPassword(req.getParameter("password"));
    }
}