package com.example.recycler.presenter.loginPresenter;

import com.example.recycler.viewLogin.logInContract;
import com.google.firebase.auth.FirebaseAuth;

public class presenterLogin implements interfaceLogin, logInContract.successfulLogin {

    private final FirebaseAuth mAuth;
    private logInContract.attachViewLogin attachViewLogin;

    public presenterLogin(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    @Override
    public void signIn(String email, String password) {

        attachViewLogin.showProgress();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                attachViewLogin.dismissProgress();
                attachViewLogin.starDashboard(mAuth.getUid());
            } else {
                attachViewLogin.dismissProgress();
                attachViewLogin.errorMessage("Correo o contraseña incorrecta");
            }
        });
    }

    @Override
    public void attachView(logInContract.attachViewLogin attachViewLogin) {
        this.attachViewLogin = attachViewLogin;
    }
}
