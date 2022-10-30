package com.example.oenskeliste.Service;

import com.example.oenskeliste.Model.User;
import com.example.oenskeliste.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Random;


@Service
public class UserService {

    private User currentUser = null;
    private UserRepository userRepository = new UserRepository();
    public static int count;



    public void createUser(WebRequest req) {
         count++;
        userRepository.createUser(new User(count,req.getParameter("email"),
                                            req.getParameter("name"),
                                            createPassword()));
    }

    public boolean login(String email, String password) {
        User user = userRepository.selectUserByMail(email);
        if (user == null) {
            return false;
        } else if (user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        } else {
            return false;
        }

    }

    public User getCurrentUser() {
        return currentUser;
    }

    private String createPassword(){
        Random r = new Random();

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\\\|;:\\'\\\",<.>/?";

        String password = "";

        for (int i = 0; i <10; i++) {
            password += chars.charAt(r.nextInt(chars.length()));
            }

        return password;

    }
}
