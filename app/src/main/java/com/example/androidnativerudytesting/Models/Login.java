package com.example.androidnativerudytesting.Models;

public class Login {

    private final String user_name;
    private final String user_password;

    public Login(String user_username, String user_password) {
        this.user_name = user_username;
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_password() {
        return user_password;
    }
}
