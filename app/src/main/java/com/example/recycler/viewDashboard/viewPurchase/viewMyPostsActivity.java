package com.example.recycler.viewDashboard.viewPurchase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.adapter.PostsAdapter;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.model.purchaseModel;
import com.example.recycler.presenter.purchasePresenter.presenterPosts;
import com.example.recycler.utilities.ConstantsUtility;
import com.example.recycler.utilities.PreferenceM;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class viewMyPostsActivity extends AppCompatActivity implements PostsContract.attachViewPosts {

    private RecyclerView mRecyclerView;
    private ShowDialog mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_posts);

        mShowDialog = new ShowDialog(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        presenterPosts mPresenterPosts = new presenterPosts(db);
        mPresenterPosts.attachView(this);
        PreferenceM mPreferenceM = new PreferenceM(this);

        mRecyclerView = findViewById(R.id.recycler_publications);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPresenterPosts.get_data(mPreferenceM.getString(ConstantsUtility.KEY_USER));
    }

    @Override
    public void set_data_posts(List<purchaseModel> modelList) {
        PostsAdapter adapter = new PostsAdapter(modelList, this);
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