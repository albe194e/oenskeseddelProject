package com.example.oenskeliste.Controller;
import com.example.oenskeliste.Service.UserService;
import com.example.oenskeliste.Service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;

@Controller
public class createController {

    private WishService wishService = new WishService();

    @PostMapping("/submitList")
    public String submitList(WebRequest req, Model model, HttpSession session) {

        if (req.getParameter("wish").length() > 1) wishService.addWishes(req, session);

        if (session.getAttribute("user") != null){
            model.addAttribute("login", "Logget ind som: " + session.getAttribute("user"));
        }

        String password = wishService.getPassword(session);

        //Thymeleaf
        model.addAttribute("date", DateFormat.getDateInstance().format(new Date()));
        model.addAttribute("name",session.getAttribute("user"));
        model.addAttribute("password",password);

        return "/getPassword";
    }
}