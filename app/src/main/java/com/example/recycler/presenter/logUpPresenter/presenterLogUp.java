package com.example.recycler.presenter.logUpPresenter;

import com.example.recycler.viewLogin.viewLogUp.logUpContract;
import com.google.firebase.auth.FirebaseAuth;

public class presenterLogUp implements interfaceLogUp, logUpContract.correctRegistration {

    private final FirebaseAuth mAuth;
    private logUpContract.attachViewLogUp attachViewLogUp;

    public presenterLogUp(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    @Override
    public void login(String Email, String password) {

        attachViewLogUp.showProgress();

        mAuth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                attachViewLogUp.dismissProgress();
                attachViewLogUp.registerUser();
            } else {
                attachViewLogUp.dismissProgress();
                attachViewLogUp.errorMessage("CORREO ELECTRÓNICO YA REGISTRADO");
            }
        });
    }

    @Override
    public void attachView(logUpContract.attachViewLogUp attachViewLogUp) {
        this.attachViewLogUp = attachViewLogUp;
    }
}
