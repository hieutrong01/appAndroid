package com.ocr.navigation;

import androidx.lifecycle.ViewModel;

import com.ocr.navigation.OOP.User;
import com.ocr.navigation.OOP.UserManager;

import java.util.ArrayList;
import java.util.List;

;

public class MainViewModel extends ViewModel {
    public List<User> users = new ArrayList<>();

    public User user = UserManager.getInstance().getCurrentUser();
}
