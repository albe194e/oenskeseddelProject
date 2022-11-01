package com.example.oenskeliste.Controller;

import com.example.oenskeliste.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class IndexController {

    private UserService userService = new UserService();

    @GetMapping("/")
    public String index(HttpSession session) {
        String UrlTemplate = "index";
        if (session.getAttribute("user") != null) {
            UrlTemplate = "redirect:/user-homepage";
        }
        return UrlTemplate;
    }

    @PostMapping("/create")
    public String create(WebRequest req) {

        boolean containsSnabelA = false;
        String email = req.getParameter("email");

        for (int i = 0; i < email.length(); i++) {
            if (email.indexOf(i) == '@') {
                containsSnabelA = true;
                break;
            }
        }

        if (Objects.requireNonNull(req.getParameter("name")).length() < 3 && !containsSnabelA) return "index";
        else {

            userService.createUser(req);
            return "/create";
        }
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp.html";
    }
}
