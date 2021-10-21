package com.example.androidnativerudytesting.interfaces;

import com.example.androidnativerudytesting.Models.User;

public interface UpdateAccountFragmentInterface {

    interface view{
        void onNavigationFragment();

        void onGetUserSuccessfully(User user);
    }

    interface presenter{
        void onUpdateAccountSuccessfully();

        void onUpdateAccountFailure();
    }
}
