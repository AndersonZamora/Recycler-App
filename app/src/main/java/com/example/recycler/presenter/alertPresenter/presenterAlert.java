package com.example.recycler.presenter.alertPresenter;

import android.app.AlertDialog;
import android.content.Context;

import com.example.recycler.R;

public class presenterAlert implements interfaceAlert, alertContract.success {

    private final Context mContext;
    private alertContract.attachView attachView;

    public presenterAlert(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void resetPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.app_name));
        builder.setMessage(mContext.getString(R.string.reset_password));
        builder.setPositiveButton(mContext.getString(R.string.toAccept), (dialog, which) -> {
            attachView.onBack();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void errorResetPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.app_name));
        builder.setMessage(mContext.getString(R.string.failed_password));
        builder.setPositiveButton(mContext.getString(R.string.toAccept), (dialog, which) -> attachView.onBack());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void attachView(alertContract.attachView attachView) {
        this.attachView = attachView;
    }
}
