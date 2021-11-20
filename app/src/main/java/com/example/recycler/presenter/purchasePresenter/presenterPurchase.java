package com.example.recycler.presenter.purchasePresenter;

import com.example.recycler.model.purchaseModel;
import com.example.recycler.viewDashboard.viewPurchase.PurchaseContract;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class presenterPurchase implements presenterPurchaseI, PurchaseContract.successfulPurchase {

    private PurchaseContract.attachViewPurchase attachViewPurchase;
    private final FirebaseFirestore db;

    public presenterPurchase(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public void get_data() {
        attachViewPurchase.showProgress();
        CollectionReference reference = db.collection("shopping");
        reference.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                List<purchaseModel> modelList = new ArrayList<>();
                QuerySnapshot document = task.getResult();
                assert document != null;
                if (!document.isEmpty()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        purchaseModel model = snapshot.toObject(purchaseModel.class).withId(snapshot.getId());
                        modelList.add(model);
                    }
                    attachViewPurchase.dismissProgress();
                } else {
                    attachViewPurchase.dismissProgress();
                    attachViewPurchase.errorMessage("No se registran manualidades");
                }
                attachViewPurchase.set_data_purchase(modelList);
            }
        });
    }

    @Override
    public void attachView(PurchaseContract.attachViewPurchase attachViewPurchase) {
        this.attachViewPurchase = attachViewPurchase;
    }
}
