package com.example.androidnativerudytesting.presentations.presenters;

import android.os.Bundle;

import com.example.androidnativerudytesting.FirebaseService.GetAccountFirebaseService;
import com.example.androidnativerudytesting.FirebaseService.UpdateAccountFirebaseService;
import com.example.androidnativerudytesting.Models.User;
import com.example.androidnativerudytesting.interfaces.UpdateAccountFragmentInterface;
import com.example.androidnativerudytesting.presentations.fragments.HomeFragment;
import com.example.androidnativerudytesting.presentations.fragments.UpdateAccountFragment;

public class UpdateAccountFragmentPresenter implements UpdateAccountFragmentInterface.presenter {

    private UpdateAccountFragmentInterface.view view;


    private String user_id;
    private String user_firstName;
    private String user_lastName;
    private String user_age;
    private String user_phone;
    private String user_address;

    //---Get User data--
    public UpdateAccountFragmentPresenter(UpdateAccountFragmentInterface.view view,String userId){
        this.view = view;
        setUser_id(userId);
        GetAccountFirebaseService getAccountFirebaseService = new GetAccountFirebaseService(this,userId);
        getAccountFirebaseService.execute();
    }

    //---Update User data--
    public UpdateAccountFragmentPresenter(
            UpdateAccountFragmentInterface.view view
            ,String user_id
            ,String firstName
            ,String lastName
            ,String phone
            ,String age
            ,String address
    ){
        this.view = view;
        setUser_id(user_id);
        setUser_firstName(firstName);
        setUser_lastName(lastName);
        setUser_age(age);
        setUser_phone(phone);
        setUser_address(address);

        startUpdateAccount();
    }


    private void startUpdateAccount(){
        User user = new User(user_id,user_firstName,user_lastName,user_age,user_phone,user_address);
        UpdateAccountFirebaseService updateAccountFirebaseService = new UpdateAccountFirebaseService(
                this,user
        );
        updateAccountFirebaseService.execute();
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_firstName(String user_firstName) {
        this.user_firstName = user_firstName;
    }

    public void setUser_lastName(String user_lastName) {
        this.user_lastName = user_lastName;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }


    @Override
    public void onUpdateAccountSuccessfully() {
        view.onNavigationFragment();
    }

    @Override
    public void onUpdateAccountFailure() {
        view.onNavigationFragment();
    }

    public void onGetAccountSuccessfully(User user){
        view.onGetUserSuccessfully(user);
    }
}
