package com.example.recycler.viewDashboard.viewEvents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.recycler.R;
import com.example.recycler.model.eventsModel;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class viewEventDetailActivity extends AppCompatActivity {

    private TextInputLayout event_detail, date_detail, description_detail;
    private ImageView image_photo_detail;
    private ProgressBar photo_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_detail);

        event_detail = findViewById(R.id.event_detail);
        date_detail = findViewById(R.id.date_detail);
        image_photo_detail = findViewById(R.id.image_photo_detail);
        photo_progress = findViewById(R.id.photo_progress);
        description_detail = findViewById(R.id.description_detail);

        findViewById(R.id.to_return).setOnClickListener(v -> onBackPressed());

        eventsModel model = (eventsModel) getIntent().getSerializableExtra("model");
        set_model(model);

        if (!model.getLink().equals("")) {
            image_photo_detail.setOnClickListener(v -> {
                Uri uri = Uri.parse(model.getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });
        }
    }

    private void set_model(eventsModel model) {

        Objects.requireNonNull(event_detail.getEditText()).setText(model.getName());
        Objects.requireNonNull(date_detail.getEditText()).setText(model.getDate());

        Picasso.get()
                .load(model.getImage())
                .into(image_photo_detail, new Callback() {
                    @Override
                    public void onSuccess() {
                        photo_progress.setVisibility(View.GONE);
                        image_photo_detail.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        photo_progress.setVisibility(View.GONE);
                        image_photo_detail.setImageResource(R.drawable.ic_round_add_a_photo_24);
                    }
                });

        Objects.requireNonNull(description_detail.getEditText()).setText(model.getDescription());
    }
}