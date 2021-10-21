package com.example.androidnativerudytesting.interfaces;

import com.example.androidnativerudytesting.Models.User;

public interface AccountDisplayFragmentInterface {

    interface view{
        void onGetUserSuccessfully(User user);
    }

    interface presenter{
        void onGetUserSuccessfully(User user);
    }
}
