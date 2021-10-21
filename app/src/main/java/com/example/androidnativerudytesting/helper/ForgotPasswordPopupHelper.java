package com.example.androidnativerudytesting.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.androidnativerudytesting.Models.Login;
import com.example.androidnativerudytesting.R;
import com.example.androidnativerudytesting.interfaces.LoginFragmentInterface;
import com.example.androidnativerudytesting.presentations.fragments.LoginFragment;
import com.example.androidnativerudytesting.presentations.presenters.LoginFragmentPresenter;

public class ForgotPasswordPopupHelper {

    private Context context;
    private LoginFragment fragment;
    private LoginFragmentPresenter presenter;
    private LoginFragmentInterface.view view;

    // view
    private EditText et_forgot_password;
    private TextView tv_showPassword;
    private Button bt_enter;
    private Button bt_back;


    public ForgotPasswordPopupHelper(
            Context context
            , LoginFragment fragment
            , LoginFragmentInterface.view view) {
        this.context = context;
        this.fragment = fragment;
        this.view = view;
    }

    public void CreatePopup(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        final View popupView = fragment.getLayoutInflater().inflate(R.layout.layout_popup_forgot_password,null);
        et_forgot_password = popupView.findViewById(R.id.editText_forgot_Password);
        tv_showPassword = popupView.findViewById(R.id.textView_show_password);
        bt_enter = popupView.findViewById(R.id.button_forgot_enter);
        bt_back = popupView.findViewById(R.id.button_forgot_back);
        tv_showPassword.setVisibility(View.GONE);
        dialogBuilder.setView(popupView);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        bt_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter = new LoginFragmentPresenter(
                        view
                        ,et_forgot_password.getText().toString()
                );
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
    }

    public void showPassword(String password){
        tv_showPassword.setVisibility(View.VISIBLE);
        tv_showPassword.setText(password);
    }

    public void hideKeyBoard(){
        tv_showPassword.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }
}

