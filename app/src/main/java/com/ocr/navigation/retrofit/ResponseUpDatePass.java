package com.ocr.navigation.retrofit;

import com.google.gson.annotations.SerializedName;

public class ResponseUpDatePass {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseUpDatePass(String message) {
        this.message = message;
    }

    @SerializedName("message")
    private String message;
}
