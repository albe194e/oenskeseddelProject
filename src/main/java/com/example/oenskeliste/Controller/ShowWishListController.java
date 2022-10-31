package com.example.oenskeliste.Controller;

import com.example.oenskeliste.Service.WishlistService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;


@RestController
public class ShowWishListController {

    WishlistService wls = new WishlistService();

    @PostMapping("/getList")
    public String getList(WebRequest req){

        ArrayList<String> wishes = wls.getAllByPassword(req);
        String name = wls.getName(req);

        String list = "";

        for (int i = 0; i < wishes.size(); i++) {
            list = list + "\n <br> - " + wishes.get(i);
        }

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>ReservedList</title>\n" +
                "    <link rel=\"stylesheet\" href=\"home.css\">\n" +
                        "\n" +
                "<a class=\"homeButton\" href=\"http://localhost:8080/\">\n" +
                "    <img src=\"homeButton.png\">\n" +
                "</a>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1 class=\"verdanaText\"> " + name + "'s ønskeliste </h1>\n" +
                "\n" +
                "<h2 class=\"verdanaText\">\n" +

                list +
                "\n" +
                "    \n" +
                "\n" +
                "</h2>\n" +
                "</body>\n" +
                "</html>";
    }
}
