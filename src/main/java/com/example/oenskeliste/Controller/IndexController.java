package com.example.oenskeliste.Controller;

import org.springframework.boot.web.server.Http2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class IndexController {


    @GetMapping("/")
    public String index(HttpSession session){
        String UrlTemplate = "index";
        if (session.getAttribute("user") != null) {
        UrlTemplate = "redirect:/user-homepage";
        }
        return UrlTemplate;
    }

    @PostMapping("/create")
    public String create(WebRequest req){

        if (Objects.requireNonNull(req.getParameter("name")).length() < 3) return "index";
        else {
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
