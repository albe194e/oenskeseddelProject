package com.example.oenskeliste.Controller;

import com.example.oenskeliste.Model.User;
import com.example.oenskeliste.Model.Wish;
import com.example.oenskeliste.Model.WishList;
import com.example.oenskeliste.Repository.WishRepository;
import com.example.oenskeliste.Service.WishService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WishController {
    WishService wishService;
    WishRepository wishRepository;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }
    //gAWBID = Get all wishes by wish id
   /* @GetMapping("/wishList/{id}")
    public String gAWBWID(@PathVariable("id") int id, Model model, HttpSession httpSession) {
        User user = (User)  httpSession.getAttribute("user");
        model.addAttribute("user", user);
        httpSession.setAttribute("wishlistSavedId", id);
        List<Wish> wish = wishService.gAWBWID(id);
        model.addAttribute("wishlist", wish);
    }*/

    @PostMapping("/add-wish")
    public String addWish(@RequestParam("name") String name,
                          @RequestParam("description") String description,
                          @RequestParam("link") String link,
                          HttpSession httpSession) {
        int wishListId = (int) httpSession.getAttribute("wishlistSavedId");
        wishService.addWish(new Wish(name, description, link, wishListId));
        return "redirect:/wishlist/" + httpSession.getAttribute("wishlistSavedId");
    }

    @PostMapping("/update-wish")
    public String updateWish(@RequestParam("id") int id,
                             @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("link") String link,
                             HttpSession httpSession) {
        wishService.updatewWishById(new Wish(id, name, description, link));
        return "redirect:/wishlist/" + httpSession.getAttribute("wishlistSavedId");
    }

    @GetMapping("/delete/{id}")
    public String deleteWish(@PathVariable("id") int id, HttpSession httpSession) {
        wishService.deleteById(id);
        return "redirect:/wishlist/" + httpSession.getAttribute("wishlistSavedId");
    }
    */

}
