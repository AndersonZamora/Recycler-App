package com.example.recycler.viewDashboard.viewPurchase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.adapter.PurchaseAdapter;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.model.purchaseModel;
import com.example.recycler.presenter.purchasePresenter.presenterPurchase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class viewPurchaseActivity extends AppCompatActivity implements PurchaseContract.attachViewPurchase {

    private RecyclerView mRecyclerView;
    private ShowDialog mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase);

        mShowDialog = new ShowDialog(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        presenterPurchase mPresenterPurchase = new presenterPurchase(db);
        mPresenterPurchase.attachView(this);

        mRecyclerView = findViewById(R.id.recycler_purchase);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPresenterPurchase.get_data();

        findViewById(R.id.to_post).setOnClickListener(v -> startActivity(new Intent(this, viewToPostActivity.class)));
        findViewById(R.id.publications).setOnClickListener(v -> startActivity(new Intent(this, viewMyPostsActivity.class)));
    }

    @Override
    public void set_data_purchase(List<purchaseModel> modelList) {
        PurchaseAdapter adapter = new PurchaseAdapter(modelList, this);
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