package com.example.recycler.presenter.dashboardPresenter;

import com.example.recycler.model.userModel;
import com.example.recycler.viewDashboard.DashboardContract;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class presenterDashboard implements presenterDashboardI, DashboardContract.successfulUser {

    private DashboardContract.attachViewUser attachViewUser;
    private final FirebaseFirestore db;

    public presenterDashboard(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public void get_data(String uid) {
        attachViewUser.showProgress();
        DocumentReference docRef = db.collection("users").document(uid);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                assert document != null;
                if (document.exists()) {
                    userModel model = document.toObject(userModel.class);
                    assert model != null;
                    attachViewUser.dismissProgress();
                    attachViewUser.set_data_user(model);
                } else {
                    attachViewUser.dismissProgress();
                    attachViewUser.errorMessage("No such document");
                }
            } else {
                attachViewUser.errorMessage(Objects.requireNonNull(task.getException()).toString());
            }
        });
    }

    @Override
    public void attachView(DashboardContract.attachViewUser attachViewUser) {
        this.attachViewUser = attachViewUser;
    }
}
