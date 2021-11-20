package com.example.recycler.viewDashboard.viewRedeemPoints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.adapter.RedeemAdapter;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.model.redeemModel;
import com.example.recycler.presenter.redeemPresenter.presenterRedeem;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class viewRedeemPointsActivity extends AppCompatActivity implements RedeemContract.attachViewRedeem {

    private RecyclerView mRecyclerView;
    private ShowDialog mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_redeem_points);

        mShowDialog = new ShowDialog(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        presenterRedeem mPresenterRedeem = new presenterRedeem(db);
        mPresenterRedeem.attachView(this);
        mPresenterRedeem.get_data();

        mRecyclerView = findViewById(R.id.recycler_redeem);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void set_data_redeem(List<redeemModel> modelList) {
        RedeemAdapter adapter = new RedeemAdapter(modelList, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        mShowDialog.loadingRequests();
    }

    @Override
    public void dismissProgress() {
        mShowDialog.DialogDismiss();
    }

    @Override
    public void errorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}