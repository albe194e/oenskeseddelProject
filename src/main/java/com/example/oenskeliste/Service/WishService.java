package com.example.oenskeliste.Service;
import com.example.oenskeliste.Repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class WishService {
    private WishRepository wishRepository = new WishRepository();

    public void addWishes(WebRequest req) {

        String[] wishes = req.getParameter("wish").split(";");

        wishRepository.addWish(wishes);
    }
}