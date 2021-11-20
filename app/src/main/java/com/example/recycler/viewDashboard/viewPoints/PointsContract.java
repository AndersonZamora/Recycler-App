package com.example.recycler.viewDashboard.viewPoints;


import com.example.recycler.model.recycleModel;

import java.util.List;

public class PointsContract {
    public interface successfulPoints {
        void attachView(attachViewPoints attachViewPoints);
    }

    public interface attachViewPoints {

        void set_data_points(List<recycleModel> modelList,int points);

        void showProgress();

        void dismissProgress();

        void errorMessage(String message);
    }
}
