package com.example.oenskeliste.Controller;
import com.example.oenskeliste.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

@Controller
public class UserController {

    private UserService userService = new UserService();

    @GetMapping("/")
    public String index(Model model, HttpSession session) {

        model.addAttribute("date", DateFormat.getDateInstance().format(new Date()));
        if (session.getAttribute("user") != null){
            model.addAttribute("login", "Logget ind som: " + session.getAttribute("user"));
        }
        return "index";
    }

    @PostMapping("/create")
    public String create(WebRequest req, Model model, HttpSession session) {

        //Thymeleaf
        model.addAttribute("date", DateFormat.getDateInstance().format(new Date()));

        if (Objects.requireNonNull(req.getParameter("name")).length() < 3) return "index";
        else {
            session.setAttribute("user", req.getParameter("name"));

            userService.createUser(req);
            if (session.getAttribute("user") != null){
                model.addAttribute("login", "Logget ind som: " + session.getAttribute("user"));
            }
            return "/create";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(HttpSession session){
        userService.deleteUser(session);
        session.invalidate();
        return "redirect:/";
    }
}