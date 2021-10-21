package com.example.androidnativerudytesting.FirebaseService;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidnativerudytesting.Models.Login;
import com.example.androidnativerudytesting.Models.User;
import com.example.androidnativerudytesting.presentations.presenters.LoginFragmentPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LoginFirebaseService implements FirebaseServiceInterface {

    private LoginFragmentPresenter listener;
    private FirebaseDatabase firebaseDatabase;

    private String userName;
    private String password;

    public LoginFirebaseService(
            LoginFragmentPresenter listener
            , Login login
            ) {
        this.listener = listener;
        setUserName(login.getUser_name());
        setPassword(login.getUser_password());
        initializeFirebase();
    }

    // Initialize Firebase Auth
    private void initializeFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance(urlDatabase);
    }

    public void execute(){
        DatabaseReference reference = firebaseDatabase.getReference("Users");
        reference.child(userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){ // username matches the username in the database
                    if (task.getResult().exists()){ // username matches the username in the database
                        DataSnapshot dataSnapshot = task.getResult();
                        boolean accessLogin = Boolean.parseBoolean(String.valueOf(dataSnapshot.child("accessLogin").getValue()));
                        // access login = true
                        if (accessLogin) {
                            String database_password = String.valueOf(dataSnapshot.child("password").getValue());
                            // The password matches user password in database.
                            if (database_password.equals(password)){
                                String id = String.valueOf(dataSnapshot.child("id").getValue());
                                String firstName = String.valueOf(dataSnapshot.child("firstName").getValue());
                                String lastName = String.valueOf(dataSnapshot.child("lastName").getValue());
                                String password = String.valueOf(dataSnapshot.child("password").getValue());
                                User user = new User(id,firstName,lastName,password);
                                listener.onLoginSuccessfully(user);
                            }else {
                                //The password does not match the user password in the database.
                                listener.onLoginFailure("Password incorrect.");
                            }
                        }else {
                            // access login = false
                            listener.onLoginFailure("This account is not access to login.");
                        }
                        }else {
                        // The username does not match the username in the database.
                        listener.onLoginFailure("Username incorrect.");
                        }
                }else {
                    //The username does not match the username in the database.
                    listener.onLoginFailure("Username incorrect.");
                }
            }
        });

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
