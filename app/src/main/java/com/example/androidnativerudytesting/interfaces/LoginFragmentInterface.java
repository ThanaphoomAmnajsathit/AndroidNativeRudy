package com.example.androidnativerudytesting.interfaces;

import com.example.androidnativerudytesting.Models.Login;
import com.example.androidnativerudytesting.Models.User;

public interface LoginFragmentInterface {

    interface view{

        void onEmailIncorrect(String reason);

        void onNavigationFragment(User user);

        void onLoginFailure(String reason);

        void onGetPasswordSuccessfully(Login login);

        void onGetPasswordFailure(String reason);
    }

    interface presenter{

        void onLoginSuccessfully(User user);

        void onLoginFailure(String reason);

        void onGetPasswordSuccessfully(Login login);

        void onGetPasswordFailure(String reason);

    }
}
