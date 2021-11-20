package com.example.recycler.presenter.purchasePresenter;

import com.example.recycler.model.purchaseModel;
import com.example.recycler.viewDashboard.viewPurchase.PostsContract;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class presenterPosts implements presenterPostsI, PostsContract.successfulPosts {

    private PostsContract.attachViewPosts attachViewPosts;
    private final FirebaseFirestore db;

    public presenterPosts(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public void get_data(String uui) {

        attachViewPosts.showProgress();
        CollectionReference reference = db.collection("shopping");
        Query query = reference.whereEqualTo("my_id", uui);

        query.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                List<purchaseModel> modelList = new ArrayList<>();
                QuerySnapshot document = task.getResult();
                assert document != null;
                if (!document.isEmpty()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        purchaseModel model = snapshot.toObject(purchaseModel.class).withId(snapshot.getId());
                        modelList.add(model);
                    }
                    attachViewPosts.dismissProgress();
                } else {
                    attachViewPosts.dismissProgress();
                    attachViewPosts.errorMessage("No se registran manualidades");
                }
                attachViewPosts.set_data_posts(modelList);
            }
        });
    }

    @Override
    public void attachView(PostsContract.attachViewPosts attachViewPosts) {
        this.attachViewPosts = attachViewPosts;
    }
}
