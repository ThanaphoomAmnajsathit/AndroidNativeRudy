package com.example.androidnativerudytesting.FirebaseService;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidnativerudytesting.Models.User;
import com.example.androidnativerudytesting.presentations.presenters.AccountDisplayFragmentPresenter;
import com.example.androidnativerudytesting.presentations.presenters.UpdateAccountFragmentPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class GetAccountFirebaseService implements FirebaseServiceInterface {

    private UpdateAccountFragmentPresenter updateListener;
    private AccountDisplayFragmentPresenter displayListener;
    private FirebaseDatabase firebaseDatabase;
    private String userId;

    public GetAccountFirebaseService(
            UpdateAccountFragmentPresenter listener
            ,String userId) {
        this.updateListener = listener;
        this.userId = userId;
        initializeFirebase();
    }

    public GetAccountFirebaseService(
            AccountDisplayFragmentPresenter listener
            ,String userId) {
        this.displayListener = listener;
        this.userId = userId;
        initializeFirebase();
    }

    // Initialize Firebase Auth
    private void initializeFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance(urlDatabase);
    }

    public void execute(){

        DatabaseReference reference = firebaseDatabase.getReference("Users");

        reference.child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String userId = String.valueOf(dataSnapshot.child("id").getValue());
                        String firstName = String.valueOf(dataSnapshot.child("firstName").getValue());
                        String lastName = String.valueOf(dataSnapshot.child("lastName").getValue());
                        String phone = String.valueOf(dataSnapshot.child("phone").getValue());
                        String age = String.valueOf(dataSnapshot.child("age").getValue());
                        String address = String.valueOf(dataSnapshot.child("address").getValue());

                        User user = new User(userId,firstName,lastName,age,phone,address);

                        if (updateListener != null){
                            updateListener.onGetAccountSuccessfully(user);
                        }else {
                            displayListener.onGetUserSuccessfully(user);
                        }

                    }
                }
            }
        });
    }

}
