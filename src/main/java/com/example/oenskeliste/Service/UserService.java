package com.example.oenskeliste.Service;

import com.example.oenskeliste.Model.User;
import com.example.oenskeliste.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private User currentUser = null;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.createUser(user);
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


}
