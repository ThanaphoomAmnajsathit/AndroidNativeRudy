package com.example.androidnativerudytesting.presentations.presenters;

import android.util.Log;

import com.example.androidnativerudytesting.FirebaseService.ForgotPasswordFirebaseService;
import com.example.androidnativerudytesting.FirebaseService.LoginFirebaseService;
import com.example.androidnativerudytesting.Models.Login;
import com.example.androidnativerudytesting.Models.User;
import com.example.androidnativerudytesting.interfaces.LoginFragmentInterface;

public class LoginFragmentPresenter implements LoginFragmentInterface.presenter {

    private LoginFragmentInterface.view view;
    private String username;
    private String password;

    // constructor login
    public LoginFragmentPresenter(
            LoginFragmentInterface.view view
            ,String username
            ,String password
    ){
        this.view =view;
        setUsername(username);
        setPassword(password);

        startLogin();
    }

    // constructor forgot password
    public LoginFragmentPresenter(
            LoginFragmentInterface.view view
            ,String username
    ){
        this.view =view;
        setUsername(username);

        startGetPassword();
    }

    private void startLogin(){
        Login login = new Login(username,password);
        LoginFirebaseService loginFirebaseService = new LoginFirebaseService(
                this, login);
        loginFirebaseService.execute();
    }

    private void startGetPassword(){
        Login login = new Login(username,null);
        ForgotPasswordFirebaseService forgotPasswordFirebaseService = new ForgotPasswordFirebaseService(this,login);
        forgotPasswordFirebaseService.execute();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void onLoginSuccessfully(User user){
        view.onNavigationFragment(user);
    }

    @Override
    public void onLoginFailure(String reason){
        view.onLoginFailure(reason);
    }

    @Override
    public void onGetPasswordSuccessfully(Login login) {
        view.onGetPasswordSuccessfully(login);
    }

    @Override
    public void onGetPasswordFailure(String reason) {
        view.onGetPasswordFailure(reason);
    }
}
