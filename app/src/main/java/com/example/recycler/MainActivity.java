package com.example.recycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.utilities.ConstantsUtility;
import com.example.recycler.utilities.PreferenceM;
import com.example.recycler.viewDashboard.viewDashboardActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private PreferenceM mPreferenceM;
    private ShowDialog mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowDialog = new ShowDialog(this);
        mShowDialog.ConnectionDialog();
        mPreferenceM = new PreferenceM(this);

        GET_DATE();
        isConnectedToInternet();
    }

    private void isConnectedToInternet() {

        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //noinspection ConstantConditions
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    mShowDialog.DialogDismiss();
                    START_ACTIVITY();
                    Log.e("SI ", "connected");
                } else {
                    Log.e("NO ", "connected");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("NO ", "connected error");
            }
        });
    }

    private void GET_DATE() {
        String currentDate = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        mPreferenceM.putString(ConstantsUtility.KEY_CURRENT_DATE, currentDate);
    }

    private void START_ACTIVITY() {
        Intent intent = new Intent(this, viewDashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}