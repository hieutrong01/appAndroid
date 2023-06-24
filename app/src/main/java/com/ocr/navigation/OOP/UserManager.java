package com.ocr.navigation.OOP;

import com.ocr.navigation.retrofit.com.ocr.navigation.User;

public class UserManager {
    private static UserManager instance;
    private boolean isLoggedIn;
    private User currentUser;

    private UserManager() {
        isLoggedIn = false;
        currentUser = null;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}
