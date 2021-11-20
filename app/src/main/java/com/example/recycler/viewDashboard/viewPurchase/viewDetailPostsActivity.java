package com.example.recycler.viewDashboard.viewPurchase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.alerts.ShowAlert;
import com.example.recycler.model.purchaseModel;
import com.example.recycler.presenter.validatePresenter.presenterValidate;
import com.example.recycler.viewDashboard.viewDashboardActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class viewDetailPostsActivity extends AppCompatActivity {

    private TextInputLayout name_detail, price_detail, quantity_detail, description_detail, whatsapp_detail;
    private ImageView image_purchase;
    private ProgressBar material_progress;
    private String uuid;
    private FirebaseFirestore db;
    private presenterValidate mValidate;
    private purchaseModel model;
    private CardView update;
    private ShowAlert mShowAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail_posts);

        mShowAlert = new ShowAlert(this);

        name_detail = findViewById(R.id.name_detail);
        price_detail = findViewById(R.id.price_detail);
        quantity_detail = findViewById(R.id.quantity_detail);
        description_detail = findViewById(R.id.description_detail);
        whatsapp_detail = findViewById(R.id.whatsapp_detail);
        image_purchase = findViewById(R.id.image_purchase);
        material_progress = findViewById(R.id.material_progress);

        mValidate = new presenterValidate(this);

        TextView to_close = findViewById(R.id.to_close);
        TextView edit = findViewById(R.id.edit);

        update = findViewById(R.id.to_return);

        uuid = getIntent().getStringExtra("uuid");

        db = FirebaseFirestore.getInstance();
        DocumentReference reference = db.collection("shopping").document(uuid);

        reference.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                model = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).toObject(purchaseModel.class)).withId(task.getResult().getId());

                edit.setVisibility(View.VISIBLE);

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

        }).addOnFailureListener(e -> Toast.makeText(this, getString(R.string.RELOAD), Toast.LENGTH_SHORT).show());

        edit.setOnClickListener(v -> {
            mShowAlert.at_time();
            update.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
            to_close.setVisibility(View.VISIBLE);
            enableEdit();
        });

        to_close.setOnClickListener(v -> {
            update.setVisibility(View.GONE);
            to_close.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
            enableClose();
        });

        update.setOnClickListener(v -> verify_data());
    }

    private void verify_data() {

        if (!mValidate.validateField(name_detail)) {
            return;
        } else {
            String name = Objects.requireNonNull(name_detail.getEditText()).getText().toString().trim();
            if (!model.getTitle().equals(name)) {
                update_document("title", name);
            }
        }

        if (!mValidate.validateField(price_detail)) {
            return;
        } else {
            if (!mValidate.validatePrice(price_detail)) {
                return;
            } else {
                String price = Objects.requireNonNull(price_detail.getEditText()).getText().toString().trim();
                if (!model.getPrice().equals(price)) {
                    update_document("price", price);
                }
            }
        }

        if (!mValidate.validateField(quantity_detail)) {
            return;
        } else {
            if (!mValidate.validateQuantityBottle(quantity_detail)) {
                return;
            } else {
                String quantity = Objects.requireNonNull(quantity_detail.getEditText()).getText().toString().trim();
                if (!model.getQuantity().equals(quantity)) {
                    update_document("quantity", quantity);
                }
            }
        }

        if (!mValidate.validateField(description_detail)) {
            return;
        } else {
            String description = Objects.requireNonNull(description_detail.getEditText()).getText().toString().trim();
            if (!model.getDescription().equals(description)) {
                update_document("description", description);
            }
        }

        if (!mValidate.validateField(whatsapp_detail)) {
            return;
        } else {
            if (!mValidate.validateNumber(whatsapp_detail)) {
                return;
            } else {
                String number = Objects.requireNonNull(whatsapp_detail.getEditText()).getText().toString().trim();
                if (!model.getNumber().equals(number)) {
                    update_document("number", number);
                }
            }
        }

        onBackPressed();
    }

    void update_document(String value, String data) {

        update.setVisibility(View.GONE);
        DocumentReference documentRef = db.collection("shopping").document(uuid);
        documentRef
                .update(value, data)
                .addOnSuccessListener(aVoid -> to_return())
                .addOnFailureListener(e -> {
                    update.setVisibility(View.VISIBLE);
                    Toast.makeText(this, getString(R.string.RELOAD), Toast.LENGTH_SHORT).show();
                });
    }

    void enableEdit() {

        name_detail.setEnabled(true);
        price_detail.setEnabled(true);
        quantity_detail.setEnabled(true);
        description_detail.setEnabled(true);
        whatsapp_detail.setEnabled(true);
    }

    void enableClose() {

        name_detail.setEnabled(false);
        price_detail.setEnabled(false);
        quantity_detail.setEnabled(false);
        description_detail.setEnabled(false);
        whatsapp_detail.setEnabled(false);
    }

    void to_return() {
        Intent intent = new Intent(this, viewDashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}