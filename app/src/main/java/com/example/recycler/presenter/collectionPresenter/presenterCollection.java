package com.example.recycler.presenter.collectionPresenter;

import com.example.recycler.model.locationModel;
import com.example.recycler.viewDashboard.viewCollectionPoint.CollectionContract;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class presenterCollection implements presenterCollectionI, CollectionContract.successfulCollection {

    private CollectionContract.attachViewCollection attachViewCollection;
    private final FirebaseFirestore db;

    public presenterCollection(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public void get_data() {
        attachViewCollection.showProgress();
        CollectionReference reference = db.collection("stockpiles");
        reference.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                List<locationModel> modelList = new ArrayList<>();

                QuerySnapshot document = task.getResult();
                assert document != null;
                if (!document.isEmpty()) {

                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        locationModel model = snapshot.toObject(locationModel.class).withId(snapshot.getId());
                        modelList.add(model);
                    }
                    attachViewCollection.dismissProgress();
                } else {
                    attachViewCollection.dismissProgress();
                    attachViewCollection.errorMessage("No se registran puntos de acopio");
                }
                attachViewCollection.set_data_collectionList(modelList);
            }
        });
    }

    @Override
    public void attachView(CollectionContract.attachViewCollection attachViewCollection) {
        this.attachViewCollection = attachViewCollection;
    }
}
