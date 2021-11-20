package com.example.recycler.viewDashboard;


import com.example.recycler.model.userModel;

public class DashboardContract {
    public interface successfulUser {
        void attachView(attachViewUser attachViewUser);
    }

    public interface attachViewUser {

        void set_data_user(userModel model);

        void showProgress();

        void dismissProgress();

        void errorMessage(String message);
    }
}
