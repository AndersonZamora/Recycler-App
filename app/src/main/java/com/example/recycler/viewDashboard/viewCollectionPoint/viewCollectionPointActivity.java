package com.example.recycler.viewDashboard.viewCollectionPoint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.fragmentMaps.mapFragment;
import com.example.recycler.model.locationModel;
import com.example.recycler.presenter.collectionPresenter.presenterCollection;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class viewCollectionPointActivity extends AppCompatActivity implements CollectionContract.attachViewCollection, mapFragment.OnFragmentInteractionListener {

    private ShowDialog mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collection_point);

        mShowDialog = new ShowDialog(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        presenterCollection mPresenterCollection = new presenterCollection(db);
        mPresenterCollection.attachView(this);
        mPresenterCollection.get_data();

    }

    @Override
    public void set_data_collectionList(List<locationModel> modelList) {

        Fragment fragment = new mapFragment(modelList);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
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