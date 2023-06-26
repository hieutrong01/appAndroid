package com.ocr.navigation.retrofit.com.ocr.navigation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {
    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;
    List<com.ocr.navigation.retrofit.com.ocr.navigation.User> result = null;

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

    public List<com.ocr.navigation.retrofit.com.ocr.navigation.User> getResult() {
        return result;
    }

    public void setResult(List<com.ocr.navigation.retrofit.com.ocr.navigation.User> result) {
        this.result = result;
    }
}
