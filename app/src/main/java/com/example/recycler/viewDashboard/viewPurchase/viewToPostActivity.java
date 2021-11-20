package com.example.recycler.viewDashboard.viewPurchase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.alerts.ShowAlert;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.model.purchaseModel;
import com.example.recycler.presenter.validatePresenter.presenterValidate;
import com.example.recycler.utilities.ConstantsUtility;
import com.example.recycler.utilities.PreferenceM;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class viewToPostActivity extends AppCompatActivity {

    private TextInputLayout name_detail, price_detail, quantity_detail, description_detail, whatsapp_detail;
    private ImageView image_purchase;
    private presenterValidate mValidate;
    private ShowAlert mShowAlert;
    private static final int KEY_GALLERY = 105;
    private Uri contentUriPost;
    private String currentPhotoPath;
    private StorageReference storageReference;
    private ShowDialog mShowDialog;
    private FirebaseFirestore firebaseStorage;
    private PreferenceM manager;
    private String name, price, quantity, description, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_to_post);

        manager = new PreferenceM(this);

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseStorage = FirebaseFirestore.getInstance();

        mShowAlert = new ShowAlert(this);
        mValidate = new presenterValidate(this);
        mShowDialog = new ShowDialog(this);

        name_detail = findViewById(R.id.name_detail);
        price_detail = findViewById(R.id.price_detail);
        quantity_detail = findViewById(R.id.quantity_detail);
        description_detail = findViewById(R.id.description_detail);
        whatsapp_detail = findViewById(R.id.whatsapp_detail);
        image_purchase = findViewById(R.id.image_purchase);


        CardView button_Send = findViewById(R.id.button_Send);
        button_Send.setOnClickListener(v -> verify_data());

        image_purchase.setOnClickListener(v -> select_photo());

        findViewById(R.id.button_cancel).setOnClickListener(v -> onBackPressed());
    }

    private void select_photo() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //noinspection deprecation
        startActivityForResult(intent, KEY_GALLERY);
    }

    private void verify_data() {

        if (currentPhotoPath != null && !currentPhotoPath.equals("")) {
            if (!mValidate.validateField(name_detail)) {
                return;
            }
            if (!mValidate.validateField(price_detail)) {
                return;
            } else {
                if (!mValidate.validatePrice(price_detail)) return;
            }
            if (!mValidate.validateField(quantity_detail)) {
                return;
            } else {
                if (!mValidate.validateQuantityBottle(quantity_detail)) return;
            }

            if (!mValidate.validateField(description_detail)) return;

            if (!mValidate.validateField(whatsapp_detail)) {
                return;
            } else {
                if (!mValidate.validateNumber(whatsapp_detail)) return;
            }
        } else {
            mShowAlert.photoPost();
            return;
        }


        SET_DATE(); // SET DATE
    }

    private void SET_DATE() {

        name = Objects.requireNonNull(name_detail.getEditText()).getText().toString().trim();
        price = Objects.requireNonNull(price_detail.getEditText()).getText().toString().trim();
        quantity = Objects.requireNonNull(quantity_detail.getEditText()).getText().toString().trim();
        description = Objects.requireNonNull(description_detail.getEditText()).getText().toString().trim();
        number = Objects.requireNonNull(whatsapp_detail.getEditText()).getText().toString().trim();

        mShowDialog.to_post();
        StorageReference image = storageReference.child("images/" + currentPhotoPath);
        image.putFile(contentUriPost).addOnSuccessListener(taskSnapshot -> image.getDownloadUrl().addOnSuccessListener(uri -> insertData(uri.toString()))).addOnFailureListener(e -> {
            mShowDialog.DialogDismiss();
            mShowAlert.OnFailurePosting();
        });
    }

    private void insertData(String toString) {

        String uui = manager.getString(ConstantsUtility.KEY_USER);
        purchaseModel purchaseModel = new purchaseModel(name, price, quantity, number, toString, description, uui);
        firebaseStorage.collection("shopping").add(purchaseModel).addOnSuccessListener(documentReference -> {
            mShowDialog.DialogDismiss();
            Toast.makeText(this, getString(R.string.sent_request), Toast.LENGTH_SHORT).show();
            onBackPressed();
        }).addOnFailureListener(e -> {
            mShowDialog.DialogDismiss();
            mShowAlert.OnFailure();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == KEY_GALLERY && resultCode == RESULT_OK) {
            Uri contentUri = data.getData();
            @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "." + getFileExt(contentUri);
            image_purchase.setImageURI(contentUri);
            contentUriPost = contentUri;
            currentPhotoPath = imageFileName;
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mine = MimeTypeMap.getSingleton();
        return mine.getExtensionFromMimeType(c.getType(contentUri));
    }
}