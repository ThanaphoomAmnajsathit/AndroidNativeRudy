package com.example.androidnativerudytesting.presentations.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidnativerudytesting.Models.Login;
import com.example.androidnativerudytesting.Models.User;
import com.example.androidnativerudytesting.R;
import com.example.androidnativerudytesting.helper.ForgotPasswordPopupHelper;
import com.example.androidnativerudytesting.interfaces.LoginFragmentInterface;
import com.example.androidnativerudytesting.presentations.activities.MainActivity;
import com.example.androidnativerudytesting.presentations.presenters.LoginFragmentPresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

public class LoginFragment extends Fragment implements LoginFragmentInterface.view {

    private LoginFragment fragment = this;
    private View view;

    //---View---
    private EditText et_username;
    private EditText et_password;
    private Button bt_login;
    private TextView tv_forgot_password;
    private ProgressBar progressBar;
    private ForgotPasswordPopupHelper popupHelper;

    //---presenter---
    private LoginFragmentPresenter presenter;

    private static User user;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    } 

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        viewXML(view);
        onClickLoginButton(this);
        onClickForgotPasswordButton(this);
    }

    private void viewXML(View view){
        et_username = view.findViewById(R.id.editText_email);
        et_password = view.findViewById(R.id.editText_password);
        bt_login = view.findViewById(R.id.button_login);
        tv_forgot_password = view.findViewById(R.id.textView_forgot_password);
        progressBar = view.findViewById(R.id.progress_login);
    }
    
    private void onClickLoginButton(LoginFragmentInterface.view view){
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter = new LoginFragmentPresenter(
                        view
                        ,et_username.getText().toString()
                        ,et_password.getText().toString()
                );

                showProgressBar();
            }
        });
    }

    private void onClickForgotPasswordButton(LoginFragmentInterface.view view){
        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupHelper = new ForgotPasswordPopupHelper(
                        getContext()
                        ,fragment
                        ,view
                );
                popupHelper.CreatePopup();
            }
        });
    }

    @Override
    public void onEmailIncorrect(String reason){
        et_username.requestFocus();
        et_username.setError(reason);
    }

    // Login Successfully
    @Override
    public void onNavigationFragment(User user){
        LoginFragment.user = user;
        NavHostFragment
                .findNavController(this)
                .navigate(R.id.action_LoginFragment_to_HomeFragment);

        hideProgressBar();
    }

    public static LoginFragment newInstance(){
        Bundle args = new Bundle();
        args.putString("firstName",user.getUser_firstName());
        args.putString("lastName",user.getUser_lastName());
        args.putString("userId",user.getUser_id());
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(args);
        return loginFragment;
    }

    @Override
    public void onLoginFailure(String reason){
        Toast.makeText(
                getContext()
                ,reason
                ,Toast.LENGTH_LONG)
                .show();

        hideProgressBar();
    }

    @Override
    public void onGetPasswordSuccessfully(Login login) {
        final String password = login.getUser_password();
        popupHelper.showPassword(password);
        popupHelper.hideKeyBoard();
    }

    @Override
    public void onGetPasswordFailure(String reason) {
        popupHelper.hideKeyBoard();
        Toast.makeText(getContext(),reason,Toast.LENGTH_SHORT).show();
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }
}