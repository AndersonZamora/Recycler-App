package com.example.recycler.viewDashboard.viewPoints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recycler.R;
import com.example.recycler.model.recycleModel;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class viewPointDetailActivity extends AppCompatActivity {

    private TextInputLayout date_day_detail, stock_code_detail, detail_quantity_bottle, detail_quantity_paperboard;
    private ImageView image_photo_detail;
    private TextView state_s;
    private ProgressBar photo_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_point_detail);

        date_day_detail = findViewById(R.id.date_day_detail);
        stock_code_detail = findViewById(R.id.stock_code_detail);
        detail_quantity_bottle = findViewById(R.id.detail_quantity_bottle);
        detail_quantity_paperboard = findViewById(R.id.detail_quantity_paperboard);
        image_photo_detail = findViewById(R.id.image_photo_detail);
        state_s = findViewById(R.id.state_s);
        photo_progress = findViewById(R.id.photo_progress);

        CardView to_return = findViewById(R.id.to_return);
        to_return.setOnClickListener(v -> onBackPressed());

        recycleModel model = (recycleModel) getIntent().getSerializableExtra("model");
        set_model(model);

    }

    private void set_model(recycleModel model) {

        state_s.setText(model.getState());
        Objects.requireNonNull(date_day_detail.getEditText()).setText(model.getDate());

        Picasso.get()
                .load(model.getPhoto())
                .into(image_photo_detail, new Callback() {
                    @Override
                    public void onSuccess() {
                        photo_progress.setVisibility(View.GONE);
                        image_photo_detail.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        image_photo_detail.setVisibility(View.VISIBLE);
                        image_photo_detail.setImageResource(R.drawable.logo);
                    }
                });

        Objects.requireNonNull(stock_code_detail.getEditText()).setText(model.getCode());
        Objects.requireNonNull(detail_quantity_bottle.getEditText()).setText(model.getBottle());
        Objects.requireNonNull(detail_quantity_paperboard.getEditText()).setText(model.getPaperboard());
    }
}