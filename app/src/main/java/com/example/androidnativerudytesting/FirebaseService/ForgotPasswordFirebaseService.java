package com.example.androidnativerudytesting.FirebaseService;

import androidx.annotation.NonNull;

import com.example.androidnativerudytesting.Models.Login;
import com.example.androidnativerudytesting.presentations.presenters.LoginFragmentPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForgotPasswordFirebaseService implements FirebaseServiceInterface {

    private FirebaseDatabase firebaseDatabase;
    private LoginFragmentPresenter listener;
    private String userId;

    public ForgotPasswordFirebaseService(LoginFragmentPresenter listener, Login login){
        this.listener = listener;
        this.userId = login.getUser_name();
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
                        String password = String.valueOf(dataSnapshot.child("password").getValue());
                        Login login = new Login(userId,password);
                        listener.onGetPasswordSuccessfully(login);
                    }else {
                        listener.onGetPasswordFailure("Username incorrect.");
                    }
                }else {
                    listener.onGetPasswordFailure("Username incorrect.");
                }
            }
        });
    }

}
