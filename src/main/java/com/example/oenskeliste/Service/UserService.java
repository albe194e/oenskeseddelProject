package com.example.oenskeliste.Service;

import com.example.oenskeliste.Model.User;
import com.example.oenskeliste.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Random;


@Service
public class UserService {

    public static User currentUser;
    private UserRepository userRepository = new UserRepository();




    public void createUser(WebRequest req) {

        User userNoPass = new User(req.getParameter("email"),
                                   req.getParameter("name"));

        if (checkUser(userNoPass)) {
            currentUser = userNoPass;
            currentUser.setPassword(userRepository.getPassword(userNoPass));

        } else {
            User newUser = new User(req.getParameter("email"),
                                    req.getParameter("name"),
                                    createPassword());

            userRepository.createUser(newUser);
            currentUser = newUser;
        }
    }

    private boolean checkUser(User user){

        return userRepository.checkIfUserExist(user);
    }

    public void deleteUser(User user){
        userRepository.deleteUser(user);
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
