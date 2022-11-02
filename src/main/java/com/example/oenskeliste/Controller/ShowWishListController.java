package com.example.oenskeliste.Controller;
import com.example.oenskeliste.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;


@Controller
public class ShowWishListController {

    WishlistService wls = new WishlistService();

    @GetMapping("/getList")
    public String getList(WebRequest req, Model model) {

        ArrayList<String> wishes = wls.getAllByPassword(req);
        String name = wls.getName(req);

        String list = "";

        for (int i = 0; i < wishes.size(); i++) {
            list += "- " + wishes.get(i) + "\n";
        }
        model.addAttribute("list",list);

        model.addAttribute("name",name);


        return "/reservedWishList";
    }
}