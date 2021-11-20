package com.example.recycler.viewDashboard.viewMaterials;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.adapter.MaterialsAdapter;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.model.materialsModel;
import com.example.recycler.presenter.materialPresenter.presenterMaterial;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class viewMaterialsActivity extends AppCompatActivity implements MaterialContract.attachViewMaterial {

    private RecyclerView mRecyclerView;
    private ShowDialog mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_materials);

        mShowDialog = new ShowDialog(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        presenterMaterial mPresenterMaterial = new presenterMaterial(db);
        mPresenterMaterial.attachView(this);

        mRecyclerView = findViewById(R.id.recycler_Materials);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPresenterMaterial.get_data();
    }

    @Override
    public void set_data_material(List<materialsModel> modelList) {
        MaterialsAdapter mAdapter = new MaterialsAdapter(modelList, this);
        mRecyclerView.setAdapter(mAdapter);
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