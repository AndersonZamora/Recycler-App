package com.example.recycler.presenter.redeemPresenter;

import com.example.recycler.model.redeemModel;
import com.example.recycler.viewDashboard.viewRedeemPoints.RedeemContract;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class presenterRedeem implements presenterRedeemI, RedeemContract.successfulRedeem {

    private RedeemContract.attachViewRedeem attachViewRedeem;
    private final FirebaseFirestore db;

    public presenterRedeem(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public void get_data() {
        attachViewRedeem.showProgress();
        CollectionReference reference = db.collection("awards");
        reference.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                List<redeemModel> modelList = new ArrayList<>();

                QuerySnapshot document = task.getResult();
                assert document != null;
                if (!document.isEmpty()) {

                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        redeemModel model = snapshot.toObject(redeemModel.class).withId(snapshot.getId());
                        modelList.add(model);
                    }
                    attachViewRedeem.dismissProgress();
                } else {
                    attachViewRedeem.dismissProgress();
                    attachViewRedeem.errorMessage("No se registran puntos lista de premios");
                }
                attachViewRedeem.set_data_redeem(modelList);
            }
        });
    }

    @Override
    public void attachView(RedeemContract.attachViewRedeem attachViewRedeem) {
        this.attachViewRedeem = attachViewRedeem;
    }
}
