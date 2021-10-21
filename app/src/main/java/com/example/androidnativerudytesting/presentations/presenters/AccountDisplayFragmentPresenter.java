package com.example.androidnativerudytesting.presentations.presenters;

import com.example.androidnativerudytesting.FirebaseService.GetAccountFirebaseService;
import com.example.androidnativerudytesting.Models.User;
import com.example.androidnativerudytesting.interfaces.AccountDisplayFragmentInterface;

public class AccountDisplayFragmentPresenter implements AccountDisplayFragmentInterface.presenter {
    private AccountDisplayFragmentInterface.view view;

    public AccountDisplayFragmentPresenter(AccountDisplayFragmentInterface.view view,String user_id){
        this.view = view;
        GetAccountFirebaseService getAccountFirebaseService = new GetAccountFirebaseService(this,user_id);
        getAccountFirebaseService.execute();
    }

    public void onGetUserSuccessfully(User user){
        view.onGetUserSuccessfully(user);
    }
}
