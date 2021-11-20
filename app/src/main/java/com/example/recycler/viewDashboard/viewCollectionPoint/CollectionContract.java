package com.example.recycler.viewDashboard.viewCollectionPoint;

import com.example.recycler.model.locationModel;

import java.util.List;

public class CollectionContract {

    public interface successfulCollection {
        void attachView(attachViewCollection attachViewCollection);
    }

    public interface attachViewCollection{

        void set_data_collectionList(List<locationModel> modelList);

        void showProgress();

        void dismissProgress();

        void errorMessage(String message);
    }
}
