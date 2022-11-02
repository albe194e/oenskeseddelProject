package com.example.oenskeliste.Controller;

import com.example.oenskeliste.Model.User;
import com.example.oenskeliste.Repository.WishListRepository;
import com.example.oenskeliste.Service.UserService;
import com.example.oenskeliste.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;

import java.util.Date;
import java.util.Objects;

@Controller
public class IndexController {

    private UserService userService = new UserService();
    WishListRepository wishlistRepo = new WishListRepository();

    @GetMapping("/")
    public String index(Model model) {


        model.addAttribute("date", DateFormat.getDateInstance().format(new Date()));
        if (UserService.currentUser != null){
            model.addAttribute("login", "Logget ind som: " +UserService.currentUser.getName());
        }

        return "index";
    }

    @PostMapping("/create")
    public String create(WebRequest req, Model model) {

        model.addAttribute("date", DateFormat.getDateInstance().format(new Date()));


        if (Objects.requireNonNull(req.getParameter("name")).length() < 3) return "index";
        else {

            userService.createUser(req);
            if (UserService.currentUser != null){
                model.addAttribute("login", "Logget ind som: " +UserService.currentUser.getName());
            }
            return "/create";
        }
    }
    @PostMapping("/deleteUser")
    public String deleteUser(){
        userService.deleteUser(UserService.currentUser);
        return "index";
    }
}
