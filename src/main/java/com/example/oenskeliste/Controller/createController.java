package com.example.oenskeliste.Controller;

import com.example.oenskeliste.Service.UserService;
import com.example.oenskeliste.Service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class createController {

    WishService wishService = new WishService();

    @PostMapping("/submitList")
    public String submitList(WebRequest req){

        if (req.getParameter("wish").length() > 1) wishService.addWishes(req);



        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <link rel=\"stylesheet\" href=\"home.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"create.css\">\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>password</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<a href=\"http://localhost:8080/\">\n" +
                "   <img class=\"skyButton\" src=\"/homeButton.png\">\n" +
                "</a>\n" +
                "\n" +
                "<h2 class=\"verdanaText\">Tillyke " + UserService.currentUser.getName() + ", du har lavet en online ønskeliste! <br>\n" +
                "                        Hvis du ønsker at dele den med familie/venner skal du sende dem koden nedeunder.</h2> <br>\n" +
                "\n" +
                "<h2 class=\"verdanaText\">Din personlige kode er: </h2>" + UserService.currentUser.getPassword()+
                "</body>\n" +
                "</html>";
    }
}
