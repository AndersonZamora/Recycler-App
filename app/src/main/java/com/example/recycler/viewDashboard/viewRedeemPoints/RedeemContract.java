package com.example.recycler.viewDashboard.viewRedeemPoints;

import com.example.recycler.model.redeemModel;

import java.util.List;

public class RedeemContract {

    public interface successfulRedeem {
        void attachView(attachViewRedeem attachViewRedeem);
    }

    public interface attachViewRedeem {

        void set_data_redeem(List<redeemModel> modelList);

        void showProgress();

        void dismissProgress();

        void errorMessage(String message);
    }
}
