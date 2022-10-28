package com.example.oenskeliste.Service;

import com.example.oenskeliste.Model.Wish;

import com.example.oenskeliste.Repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private WishRepository wishRepository;
    private WishlistService wishlistService;

    public WishService(WishRepository wishRepository, WishlistService wishlistService) {
        this.wishRepository = wishRepository;
        this.wishlistService = wishlistService;
    }

    public void addWish(Wish wish) {
        wishRepository.addWish(wish);
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
