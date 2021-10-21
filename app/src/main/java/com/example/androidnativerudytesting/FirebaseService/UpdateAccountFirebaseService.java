package com.example.androidnativerudytesting.FirebaseService;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidnativerudytesting.Models.User;
import com.example.androidnativerudytesting.presentations.presenters.UpdateAccountFragmentPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateAccountFirebaseService implements FirebaseServiceInterface {

    private UpdateAccountFragmentPresenter listener;
    private FirebaseDatabase firebaseDatabase;
    private User user;

    public UpdateAccountFirebaseService(
            UpdateAccountFragmentPresenter listener
            ,User user) {
        this.listener = listener;
        this.user = user;
        initializeFirebase();
    }

    // Initialize Firebase Auth
    private void initializeFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance(urlDatabase);
    }

    public void execute(){

        DatabaseReference reference = firebaseDatabase.getReference("Users");

        Map<String,Object> values = new HashMap<>();
        values.put("firstName",user.getUser_firstName());
        values.put("lastName",user.getUser_lastName());
        values.put("phone",user.getUser_phone());
        values.put("age",user.getUser_age());
        values.put("address",user.getUser_address());

        reference.child(user.getUser_id()).updateChildren(values).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    listener.onUpdateAccountSuccessfully();
                }else {
                    listener.onUpdateAccountFailure();
                }
            }
        });
    }

}
