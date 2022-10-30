package com.example.oenskeliste.Controller;


import com.example.oenskeliste.Model.User;
import com.example.oenskeliste.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WishlistController {

    /*
    public WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/user-homepage")
    public String userHomepage(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("wishlist", wishlistService.getAllById(user.getId()));
        return "user-home-page";
    }
    */
}
