package com.example.recycler.viewDashboard.viewEvents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.adapter.EventsAdapter;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.model.eventsModel;
import com.example.recycler.presenter.eventsPresenter.presenterEvents;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class viewEventsActivity extends AppCompatActivity implements EventsContract.attachViewEvents {

    private RecyclerView mRecyclerView;
    private ShowDialog mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        mShowDialog = new ShowDialog(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        presenterEvents mPresenterEvents = new presenterEvents(db);
        mPresenterEvents.attachView(this);

        mRecyclerView = findViewById(R.id.recycler_events);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPresenterEvents.get_data();
    }

    @Override
    public void set_data_events(List<eventsModel> modelList) {
        EventsAdapter adapter = new EventsAdapter(modelList, this);
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