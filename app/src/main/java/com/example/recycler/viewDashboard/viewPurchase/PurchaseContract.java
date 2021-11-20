package com.example.recycler.viewDashboard.viewPurchase;

import com.example.recycler.model.purchaseModel;

import java.util.List;

public class PurchaseContract {
    public interface successfulPurchase {
        void attachView(attachViewPurchase attachViewPurchase);
    }

    public interface attachViewPurchase {

        void set_data_purchase(List<purchaseModel> modelList);

        void showProgress();

        void dismissProgress();

        void errorMessage(String message);
    }
}
