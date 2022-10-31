package com.example.oenskeliste.Service;

import com.example.oenskeliste.Model.Wish;

import com.example.oenskeliste.Repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Service
public class WishService {

    private WishRepository wishRepository = new WishRepository();
    private WishlistService wishlistService;


    public void addWishes(WebRequest req) {



        String[] wishes = req.getParameter("wish").split(";");

        wishRepository.addWish(wishes);
    }

    public void deleteById(int id) {
        wishRepository.deleteWish(id);
    }

    public List<Wish> gAWBWID(int id) {
        return wishRepository.findListById(id);
    }

    public void updatewWishById(Wish wish) {
        wishRepository.updateWish(wish);
    }
}
