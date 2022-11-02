package com.example.oenskeliste.Controller;
import com.example.oenskeliste.Service.UserService;
import com.example.oenskeliste.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
@Controller
public class ShowWishListController {
    WishlistService wls = new WishlistService();

    @GetMapping("/getList")
    public String getList(WebRequest req, Model model) {

        model.addAttribute("date", DateFormat.getDateInstance().format(new Date()));

        if (UserService.currentUser != null){
            model.addAttribute("login", "Logget ind som: " +UserService.currentUser.getName());
        }

        ArrayList<String> wishes = wls.getAllByPassword(req);
        String name = wls.getName(req);

        String list = "";

        for (int i = 0; i < wishes.size(); i++) {
            list += "- " + wishes.get(i) + "\n";
        }
        //Thymeleaf
        model.addAttribute("list",list);
        model.addAttribute("name",name);

        return "/reservedWishList";
    }
}