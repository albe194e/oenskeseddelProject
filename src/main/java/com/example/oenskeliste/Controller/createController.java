package com.example.oenskeliste.Controller;
import com.example.oenskeliste.Service.UserService;
import com.example.oenskeliste.Service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.util.Date;

@Controller
public class createController {

    WishService wishService = new WishService();

    @PostMapping("/submitList")
    public String submitList(WebRequest req, Model model) {

        if (req.getParameter("wish").length() > 1) wishService.addWishes(req);
        if (UserService.currentUser != null){
            model.addAttribute("login", "Logget ind som: " +UserService.currentUser.getName());
        }

        String password = UserService.currentUser.getPassword();
        String name = UserService.currentUser.getName();

        model.addAttribute("date", DateFormat.getDateInstance().format(new Date()));
        model.addAttribute("login", "Logget ind som: " +UserService.currentUser.getName());

        model.addAttribute("name",name);
        model.addAttribute("password",password);


        return "/getPassword";
    }
}
