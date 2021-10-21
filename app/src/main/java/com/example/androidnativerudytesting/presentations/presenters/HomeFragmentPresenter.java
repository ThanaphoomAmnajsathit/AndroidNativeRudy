package com.example.androidnativerudytesting.presentations.presenters;

import com.example.androidnativerudytesting.interfaces.HomeFragmentInterface;

public class HomeFragmentPresenter implements HomeFragmentInterface.presenter {

    private HomeFragmentInterface.view view;

    public HomeFragmentPresenter(HomeFragmentInterface.view view){
        this.view = view;
    }
}
