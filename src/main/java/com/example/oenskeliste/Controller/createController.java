package com.example.oenskeliste.Controller;

import com.example.oenskeliste.Service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class createController {

    WishService wishService = new WishService();

    @PostMapping("/submitList")
    public String submitList(WebRequest req){

        wishService.addWish(req);

        return "index";
    }



}
