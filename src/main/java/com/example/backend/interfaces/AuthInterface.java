package com.example.backend.interfaces;
import com.example.backend.model.User;

import java.util.Map;

public interface AuthInterface {
    String login(String username, String password);
    User register(User user);
    User getUserByUsername(String username);
    Map<String, Object> getUserInfo(String username);
}
