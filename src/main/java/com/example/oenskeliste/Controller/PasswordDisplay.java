package com.example.oenskeliste.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordDisplay {

    @GetMapping("/displayPassword")
    public String showPassword(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <link rel=\"stylesheet\" href=\"index.css\">\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>password</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<a class=\"homeButton\" href=\"window.location.href='http://localhost:8080/';\">\n" +
                "    <img src=\"homebutton.png\">\n" +
                "</a>\n" +
                "\n" +
                "<h2 class=\"verdanaText\">Tillyke insertname har lavet en online ønskeliste! <br>\n" +
                "                        Hvis du ønsker at dele den med familie/venner skal du sende dem koden nedeunder.</h2> <br>\n" +
                "\n" +
                "<h2 class=\"verdanaText\">Din personlige kode er: insertkode</h2>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }



    /*
    public WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/user-homepage")
    public String userHomepage(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("wishlist", wishlistService.getAllById(user.getId()));
        return "user-home-page";
    }
    */
}
