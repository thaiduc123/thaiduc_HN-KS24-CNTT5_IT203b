package service;

import dao.UserDAO;
import model.User;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }
    public boolean register(User user) {
        return userDAO.register(user);
    }
}