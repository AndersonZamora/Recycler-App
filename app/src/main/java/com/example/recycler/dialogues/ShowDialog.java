package com.example.recycler.dialogues;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.recycler.R;

public class ShowDialog {

    private final Context mContext;
    private ProgressDialog mDialog;

    public ShowDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void ConnectionDialog() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(mContext.getString(R.string.checking_connection));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void LocationDialog() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(mContext.getString(R.string.getting_location));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void SendingRequest() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(mContext.getString(R.string.sending_request));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void to_post() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(mContext.getString(R.string.posting));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void LoadingData() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(mContext.getString(R.string.loading_data));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void loadingRequests() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(mContext.getString(R.string.loading_approved));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void DialogDismiss() {
        mDialog.dismiss();
    }
}
