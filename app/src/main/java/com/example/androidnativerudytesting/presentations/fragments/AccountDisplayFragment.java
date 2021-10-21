package com.example.androidnativerudytesting.presentations.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.androidnativerudytesting.Models.User;
import com.example.androidnativerudytesting.R;
import com.example.androidnativerudytesting.adapter.AccountDisplayAdapter;
import com.example.androidnativerudytesting.interfaces.AccountDisplayFragmentInterface;
import com.example.androidnativerudytesting.presentations.presenters.AccountDisplayFragmentPresenter;


public class AccountDisplayFragment extends Fragment implements AccountDisplayFragmentInterface.view {

    AccountDisplayFragment fragment = this;

    private AccountDisplayFragmentPresenter presenter;

    //---View---
    private RecyclerView recyclerView;
    private Button bt_back;
    private AccountDisplayAdapter adapter;

    //---Adapter recyclerview---
    private int[] topic = {R.drawable.name,R.drawable.telephone,R.drawable.age,R.drawable.address};

    private String userId;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_display, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewXML(view);
        presenter = new AccountDisplayFragmentPresenter(fragment,userId);
        onClickButtonBack();
    }

    private void viewXML(View view){
        recyclerView = view.findViewById(R.id.display_account_recyclerview);
        bt_back = view.findViewById(R.id.button_back);
    }

    @Override
    public void onGetUserSuccessfully(User user) {
        setAdapter(user);
    }

    private void setAdapter(User user){
        String[] information = {
                user.getUser_firstName()+" "+user.getUser_lastName()
                ,user.getUser_phone()
                ,user.getUser_age()
                ,user.getUser_address()
        };
        adapter = new AccountDisplayAdapter(topic,information);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void onClickButtonBack(){
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(fragment).popBackStack();
            }
        });
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}