package com.example.recycler.presenter.registrationPresenter;

import com.example.recycler.viewLogin.viewLogUp.registrationContract;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class presenterRegistration implements interfaceRegistration, registrationContract.correctRegistration {

    private final FirebaseFirestore mFirestore;
    private registrationContract.attachViewRegistration attachViewRegistration;

    public presenterRegistration(FirebaseFirestore mFirestore) {
        this.mFirestore = mFirestore;
    }

    @Override
    public void register(Map<String, Object> model, String uid) {

        attachViewRegistration.showProgress();
        mFirestore.collection("users").document(uid).set(model).addOnSuccessListener(unused -> {
            attachViewRegistration.starDashboard();
            attachViewRegistration.dismissProgress();
        }).addOnFailureListener(e -> {
            attachViewRegistration.errorMessage("INTENTE DE NUEVO");
            attachViewRegistration.dismissProgress();
        });
    }

    @Override
    public void attachView(registrationContract.attachViewRegistration attachViewRegistration) {
        this.attachViewRegistration = attachViewRegistration;
    }
}
