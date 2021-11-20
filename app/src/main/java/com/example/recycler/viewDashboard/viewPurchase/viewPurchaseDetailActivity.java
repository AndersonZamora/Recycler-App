package com.example.recycler.viewDashboard.viewPurchase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.recycler.R;
import com.example.recycler.alerts.ShowAlert;
import com.example.recycler.model.purchaseModel;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class viewPurchaseDetailActivity extends AppCompatActivity {

    private TextInputLayout name_detail, price_detail, quantity_detail, description_detail, whatsapp_detail;
    private ImageView image_purchase;
    private ProgressBar material_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase_detail);

        name_detail = findViewById(R.id.name_detail);
        price_detail = findViewById(R.id.price_detail);
        quantity_detail = findViewById(R.id.quantity_detail);
        description_detail = findViewById(R.id.description_detail);
        whatsapp_detail = findViewById(R.id.whatsapp_detail);
        image_purchase = findViewById(R.id.image_purchase);
        ImageView whatsapp_img = findViewById(R.id.whatsapp_img);
        material_progress = findViewById(R.id.material_progress);

        findViewById(R.id.to_return).setOnClickListener(v -> onBackPressed());

        purchaseModel model = (purchaseModel) getIntent().getSerializableExtra("model");
        ShowAlert mShowAlert = new ShowAlert(this);

        whatsapp_img.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String uri = "whatsapp://send?phone=+51" + model.getNumber() + "&text=" + getString(R.string.publication) + " : " + model.getTitle() + " en " + getString(R.string.app_name);
            try {
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                mShowAlert.AlertMe();
            }
        });
        set_model(model);
    }

    private void set_model(purchaseModel model) {

        Objects.requireNonNull(name_detail.getEditText()).setText(model.getTitle());
        Objects.requireNonNull(price_detail.getEditText()).setText(model.getPrice());
        Objects.requireNonNull(quantity_detail.getEditText()).setText(model.getQuantity());
        Picasso.get()
                .load(model.getImage())
                .into(image_purchase, new Callback() {
                    @Override
                    public void onSuccess() {
                        material_progress.setVisibility(View.GONE);
                        image_purchase.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        material_progress.setVisibility(View.VISIBLE);
                        image_purchase.setImageResource(R.drawable.logo);
                    }
                });

        Objects.requireNonNull(description_detail.getEditText()).setText(model.getDescription());
        Objects.requireNonNull(whatsapp_detail.getEditText()).setText(model.getNumber());
    }
}