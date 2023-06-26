package com.ocr.navigation;

import androidx.lifecycle.ViewModel;

import com.ocr.navigation.OOP.UserManager;
import com.ocr.navigation.retrofit.com.ocr.navigation.User;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    public List<User> users = new ArrayList<>();

    public User user = UserManager.getInstance().getCurrentUser();
}
