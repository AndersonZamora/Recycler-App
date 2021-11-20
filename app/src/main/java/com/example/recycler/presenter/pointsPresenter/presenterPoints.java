package com.example.recycler.presenter.pointsPresenter;

import com.example.recycler.model.recycleModel;
import com.example.recycler.viewDashboard.viewPoints.PointsContract;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class presenterPoints implements presenterPointsI, PointsContract.successfulPoints {

    private PointsContract.attachViewPoints attachViewPoints;
    private final FirebaseFirestore db;

    public presenterPoints(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public void get_data(String uid) {

        attachViewPoints.showProgress();
        CollectionReference reference = db.collection("requests");
        Query query = reference.whereEqualTo("conId", uid);
        query.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                List<recycleModel> modelList = new ArrayList<>();
                int points = 0;
                QuerySnapshot document = task.getResult();
                assert document != null;
                if (!document.isEmpty()) {

                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        recycleModel model = snapshot.toObject(recycleModel.class);
                        if (model.conId.equals(uid)) {
                            points += Integer.parseInt(model.getPoints());
                            modelList.add(model);
                        }
                    }
                    attachViewPoints.dismissProgress();
                } else {
                    attachViewPoints.dismissProgress();
                    attachViewPoints.errorMessage("No se registran solicitudes");
                }
                attachViewPoints.set_data_points(modelList, points);
            }
        });
    }

    @Override
    public void attachView(PointsContract.attachViewPoints attachViewPoints) {
        this.attachViewPoints = attachViewPoints;
    }
}
