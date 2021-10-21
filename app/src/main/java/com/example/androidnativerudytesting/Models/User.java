package com.example.androidnativerudytesting.Models;

public class User {

    private String user_id;
    private String user_firstName;
    private String user_lastName;
    private String user_password;
    private String user_age;
    private String user_phone;
    private String user_address;

    public User(String user_id
            , String user_firstName
            , String user_lastName
            , String user_age
            , String user_phone
            , String user_address) {
        this.user_id = user_id;
        this.user_firstName = user_firstName;
        this.user_lastName = user_lastName;
        this.user_age = user_age;
        this.user_phone = user_phone;
        this.user_address = user_address;
    }

    public User(String id
            ,String user_firstName
            , String user_lastName
            ,String user_password) {
        this.user_id = id;
        this.user_firstName = user_firstName;
        this.user_lastName = user_lastName;
        this.user_password = user_password;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_firstName() {
        return user_firstName;
    }

    public String getUser_lastName() {
        return user_lastName;
    }


    public String getUser_age() {
        return user_age;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public String getUser_password() {
        return user_password;
    }
}
