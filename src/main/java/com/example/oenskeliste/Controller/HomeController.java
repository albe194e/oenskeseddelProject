package com.example.oenskeliste.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {



    @GetMapping("/")
    public String index(){
        return "index";
    }
    @PostMapping("/createUser")
    public String createUser(WebRequest req){

        if (req.getParameter("name").length() < 3) return "index";
        else {
            return "/makeWishList";
        }

    }

}
