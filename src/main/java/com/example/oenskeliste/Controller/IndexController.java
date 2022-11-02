package com.example.oenskeliste.Controller;

import com.example.oenskeliste.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Controller
public class IndexController {

    private UserService userService = new UserService();

    @GetMapping("/")
    public String index(HttpSession session, Model model) {

        model.addAttribute("date", DateFormat.getDateInstance().format(new Date()));
        String UrlTemplate = "index";
        if (session.getAttribute("user") != null) {
            UrlTemplate = "redirect:/user-homepage";
        }
        return UrlTemplate;
    }

    @PostMapping("/create")
    public String create(WebRequest req, Model model) {

        String email = req.getParameter("email");

        boolean containsSnabelA = email.contains("@");

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
