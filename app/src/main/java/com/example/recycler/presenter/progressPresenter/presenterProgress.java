package com.example.recycler.presenter.progressPresenter;

import android.app.ProgressDialog;

public class presenterProgress implements interfaceProgress {

    private final ProgressDialog mDialog;

    public presenterProgress(ProgressDialog mDialog) {
        this.mDialog = mDialog;
    }

    @Override
    public void showProgress(String message) {
        mDialog.setMessage(message);
        mDialog.setCancelable(false);
        mDialog.show();
    }

    @Override
    public void dismissProgress() {
        mDialog.dismiss();
    }

}
