package com.ocr.navigation.retrofit;

import com.google.gson.annotations.SerializedName;
import com.ocr.navigation.OOP.User;

import java.util.List;

public class UserModel {
    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;
    List<User> result = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
