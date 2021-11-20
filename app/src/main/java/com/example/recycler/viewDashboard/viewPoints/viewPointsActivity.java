package com.example.recycler.viewDashboard.viewPoints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.adapter.pointsAdapter;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.model.recycleModel;
import com.example.recycler.presenter.pointsPresenter.presenterPoints;
import com.example.recycler.utilities.ConstantsUtility;
import com.example.recycler.utilities.PreferenceM;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class viewPointsActivity extends AppCompatActivity implements PointsContract.attachViewPoints {

    private RecyclerView mRecyclerView;
    private TextView total_points;
    private ShowDialog mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_points);

        mShowDialog = new ShowDialog(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        PreferenceM mPreferenceM = new PreferenceM(this);

        mRecyclerView = findViewById(R.id.recycler_points);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        total_points = findViewById(R.id.total_points);

        presenterPoints mPresenterPoints = new presenterPoints(db);
        mPresenterPoints.attachView(this);

        String uid = mPreferenceM.getString(ConstantsUtility.KEY_USER);
        mPresenterPoints.get_data(uid);

    }


    @Override
    public void set_data_points(List<recycleModel> modelList, int points) {
        total_points.setText(String.valueOf(points));
        pointsAdapter mAdapter = new pointsAdapter(modelList, this);
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