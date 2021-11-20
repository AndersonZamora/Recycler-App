package com.example.recycler.alerts;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.widget.Button;

import androidx.core.app.ActivityCompat;

import com.example.recycler.R;
import com.example.recycler.viewDashboard.viewRecycler.viewRecyclerActivity;

public class ShowAlert {

    private final Context mContext;

    public ShowAlert(Context mContext) {
        this.mContext = mContext;
    }

    public void GPS_is_disabled() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.Activate_GPS))
                .setPositiveButton(mContext.getString(R.string.confirm), (dialog, which) -> mContext.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)));
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));

    }

    public void permission_request(int code, viewRecyclerActivity view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if (ActivityCompat.shouldShowRequestPermissionRationale(view, Manifest.permission.ACCESS_FINE_LOCATION)) {

            builder.setTitle(mContext.getString(R.string.Permission_request));
            builder.setCancelable(false);
            builder.setMessage(mContext.getString(R.string.on_the_map));
            builder.setPositiveButton(mContext.getString(R.string.Conf), (dialog, which) -> ActivityCompat.requestPermissions(view, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, code));
            builder.setNegativeButton(mContext.getString(R.string.Cancel), (dialog, which) -> dialog.dismiss());
            AlertDialog dialogBu = builder.create();
            dialogBu.setCancelable(false);
            dialogBu.show();
            Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setTextColor(Color.parseColor("#FF000000"));
            Button negativeButton = dialogBu.getButton(AlertDialog.BUTTON_NEGATIVE);
            negativeButton.setTextColor(Color.parseColor("#FF000000"));

        } else {
            ActivityCompat.requestPermissions(view, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, code);
        }
    }

    public void coordinate() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.collection_location));
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.coordinate_message));
        builder.setPositiveButton(mContext.getString(R.string.Conf), (dialog, which) -> dialog.dismiss());
        AlertDialog dialogBu = builder.create();
        dialogBu.setCancelable(false);
        dialogBu.show();
        Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
    }

    public void photo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.stock_photo));
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.photo_message));
        builder.setPositiveButton(mContext.getString(R.string.Conf), (dialog, which) -> dialog.dismiss());
        AlertDialog dialogBu = builder.create();
        dialogBu.setCancelable(false);
        dialogBu.show();
        Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
    }

    public void photoPost() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string._photo));
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.for_posting));
        builder.setPositiveButton(mContext.getString(R.string.Conf), (dialog, which) -> dialog.dismiss());
        AlertDialog dialogBu = builder.create();
        dialogBu.setCancelable(false);
        dialogBu.show();
        Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
    }

    public void Quantity() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.number_units));
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.select_least));
        builder.setPositiveButton(mContext.getString(R.string.Conf), (dialog, which) -> dialog.dismiss());
        AlertDialog dialogBu = builder.create();
        dialogBu.setCancelable(false);
        dialogBu.show();
        Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
    }

    //at_time
    public void OnFailure() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.error));
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.error_occurred));
        builder.setPositiveButton(mContext.getString(R.string.Conf), (dialog, which) -> dialog.dismiss());
        AlertDialog dialogBu = builder.create();
        dialogBu.setCancelable(false);
        dialogBu.show();
        Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
    }

    public void at_time() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.Alert));
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.at_time));
        builder.setPositiveButton(mContext.getString(R.string.Conf), (dialog, which) -> dialog.dismiss());
        AlertDialog dialogBu = builder.create();
        dialogBu.setCancelable(false);
        dialogBu.show();
        Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
    }


    public void OnFailurePosting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.error));
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.posting_art));
        builder.setPositiveButton(mContext.getString(R.string.Conf), (dialog, which) -> dialog.dismiss());
        AlertDialog dialogBu = builder.create();
        dialogBu.setCancelable(false);
        dialogBu.show();
        Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
    }

    public void AnErrorOccurred(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.an_error_occurred));
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.an_error_message) + "message :" + message);
        builder.setPositiveButton(mContext.getString(R.string.Conf), (dialog, which) -> dialog.dismiss());
        AlertDialog dialogBu = builder.create();
        dialogBu.setCancelable(false);
        dialogBu.show();
        Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
    }

    public void AlertMe() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Whatsapp");
        builder.setCancelable(false);
        builder.setMessage(mContext.getString(R.string.try_again));
        builder.setPositiveButton(mContext.getString(R.string.confirm), null);
        AlertDialog dialogBu = builder.create();
        dialogBu.show();
        Button positiveButton = dialogBu.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor(mContext.getString(R.string.color)));
    }

}
