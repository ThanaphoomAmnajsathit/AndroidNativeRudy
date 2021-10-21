package com.example.androidnativerudytesting.presentations.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidnativerudytesting.R;
import com.example.androidnativerudytesting.interfaces.HomeFragmentInterface;

public class HomeFragment extends Fragment implements HomeFragmentInterface.view {

    HomeFragment fragment = this;

    //--view--
    private TextView et_userName_header;
    private ImageView iv_updateAccount;
    private ImageView iv_accountDisplay;

    private static String firstName;
    private static String lastName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginFragment loginFragment = LoginFragment.newInstance();
        Bundle bundle = loginFragment.getArguments();
        if (bundle != null){
            setFirstName(bundle.getString("firstName"));
            setLastName(bundle.getString("lastName"));
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewXML(view);
        setUserNameHeader();
        onClickUpdateAccount();
        onClickAccountDisplay();
    }

    private void viewXML(View view){
        et_userName_header = view.findViewById(R.id.textView_userName_Header);
        iv_updateAccount = view.findViewById(R.id.imageView_updateAccount);
        iv_accountDisplay = view.findViewById(R.id.imageView_accountDisplay);
    }

    private void setUserNameHeader(){
        et_userName_header.setText(firstName+" "+lastName);
    }

    private void onClickUpdateAccount(){
        iv_updateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(fragment).navigate(R.id.action_SecondFragment_to_updateAccountFragment);
            }
        });
    }

    private void onClickAccountDisplay(){
        iv_accountDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(fragment).navigate(R.id.action_SecondFragment_to_accountDisplayFragment);
            }
        });
    }


    public void setFirstName(String firstName) {
        HomeFragment.firstName = firstName;
    }

    public void setLastName(String lastName) {
        HomeFragment.lastName = lastName;
    }

}