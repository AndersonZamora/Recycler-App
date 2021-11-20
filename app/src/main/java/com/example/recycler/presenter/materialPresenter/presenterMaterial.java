package com.example.recycler.presenter.materialPresenter;

import com.example.recycler.model.materialsModel;
import com.example.recycler.viewDashboard.viewMaterials.MaterialContract;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class presenterMaterial implements presenterMaterialI, MaterialContract.successfulMaterial {

    private MaterialContract.attachViewMaterial attachViewMaterial;
    private final FirebaseFirestore db;

    public presenterMaterial(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public void get_data() {

        attachViewMaterial.showProgress();
        CollectionReference reference = db.collection("materials");
        reference.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                List<materialsModel> modelList = new ArrayList<>();
                QuerySnapshot document = task.getResult();
                assert document != null;
                if (!document.isEmpty()) {

                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        materialsModel model = snapshot.toObject(materialsModel.class).withId(snapshot.getId());
                        modelList.add(model);
                    }
                    attachViewMaterial.dismissProgress();
                } else {
                    attachViewMaterial.dismissProgress();
                    attachViewMaterial.errorMessage("No se registran materiales");
                }
                attachViewMaterial.set_data_material(modelList);
            }
        });
    }

    @Override
    public void attachView(MaterialContract.attachViewMaterial attachViewMaterial) {
        this.attachViewMaterial = attachViewMaterial;
    }
}
