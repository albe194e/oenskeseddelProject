package com.example.oenskeliste.Service;
import com.example.oenskeliste.Repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Service
public class WishService {

    private WishRepository wishRepository = new WishRepository();

    public void addWishes(WebRequest req, HttpSession session) {

        String[] wishes = req.getParameter("wish").split(";");

        wishRepository.addWish(wishes, (String) session.getAttribute("user"));
    }
    public String getPassword(HttpSession session){
        return wishRepository.getPassword((String) session.getAttribute("user"));
    }
}