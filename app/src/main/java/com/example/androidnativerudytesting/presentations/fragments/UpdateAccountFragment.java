package com.example.androidnativerudytesting.presentations.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidnativerudytesting.FirebaseService.GetAccountFirebaseService;
import com.example.androidnativerudytesting.Models.User;
import com.example.androidnativerudytesting.R;
import com.example.androidnativerudytesting.interfaces.UpdateAccountFragmentInterface;
import com.example.androidnativerudytesting.presentations.presenters.UpdateAccountFragmentPresenter;


public class UpdateAccountFragment extends Fragment implements UpdateAccountFragmentInterface.view {

    UpdateAccountFragment fragment = this;
    private UpdateAccountFragmentPresenter presenter;

    private String userId;

    //---View---
    private EditText et_firstName;
    private EditText et_lastname;
    private EditText et_phone;
    private EditText et_age;
    private EditText et_address;
    private Button bt_save;
    private Button bt_cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginFragment loginFragment = LoginFragment.newInstance();
        Bundle bundle = loginFragment.getArguments();
        if (bundle != null){
            setUserId(bundle.getString("userId"));
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_account, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewXML(view);
        onClickButtonSave();
        onClickButtonCancel();
        presenter = new UpdateAccountFragmentPresenter(this,userId);
    }

    private void viewXML(View view){
        et_firstName = view.findViewById(R.id.editText_update_firstName);
        et_lastname = view.findViewById(R.id.editText_update_lastName);
        et_phone = view.findViewById(R.id.editText_update_phone);
        et_age = view.findViewById(R.id.editText_update_age);
        et_address = view.findViewById(R.id.editText_update_address);
        bt_save = view.findViewById(R.id.button_save);
        bt_cancel = view.findViewById(R.id.button_cancel);
    }

    private void onClickButtonSave(){
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter = new UpdateAccountFragmentPresenter(
                        fragment
                        ,userId
                        ,et_firstName.getText().toString()
                        ,et_lastname.getText().toString()
                        ,et_phone.getText().toString()
                        ,et_age.getText().toString()
                        ,et_address.getText().toString()
                );
            }
        });
    }

    private void onClickButtonCancel(){
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(fragment).popBackStack();
            }
        });
    }

    @Override
    public void onNavigationFragment() {
        NavHostFragment.findNavController(fragment).popBackStack();
    }

    @Override
    public void onGetUserSuccessfully(User user) {
        setUserDataToEditText(user);
    }

    private void setUserDataToEditText(User user){
        et_firstName.setText(user.getUser_firstName());
        et_lastname.setText(user.getUser_lastName());
        et_phone.setText(user.getUser_phone());
        et_age.setText(user.getUser_age());
        et_address.setText(user.getUser_address());
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}