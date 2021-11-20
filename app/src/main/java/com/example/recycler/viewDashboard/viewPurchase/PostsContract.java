package com.example.recycler.viewDashboard.viewPurchase;

import com.example.recycler.model.purchaseModel;

import java.util.List;

public class PostsContract {
    public interface successfulPosts {
        void attachView(attachViewPosts attachViewPosts);
    }

    public interface attachViewPosts {

        void set_data_posts(List<purchaseModel> modelList);

        void showProgress();

        void dismissProgress();

        void errorMessage(String message);
    }
}
