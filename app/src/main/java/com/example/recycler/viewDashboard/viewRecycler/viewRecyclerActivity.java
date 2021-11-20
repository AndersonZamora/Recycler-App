package com.example.recycler.viewDashboard.viewRecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.recycler.R;
import com.example.recycler.alerts.ShowAlert;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.model.recycleModel;
import com.example.recycler.presenter.validatePresenter.presenterValidate;
import com.example.recycler.utilities.ConstantsUtility;
import com.example.recycler.utilities.PreferenceM;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class viewRecyclerActivity extends AppCompatActivity {

    private CheckBox check_bottle, check_paperboard;
    private TextInputLayout check_quantity_bottle, check_quantity_paperboard, date_day, stock_code;
    private ShowDialog mShowDialog;
    private ShowAlert mShowAlert;
    private CardView get_location, get_location_ok;
    private ImageView image_photo;
    private String currentPhotoPath;
    private final int LOCATION_PER_CODE = 2;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String latS, lenS, dateS, codeS, bottleS, paperboardS, namePhoto;
    private Uri contentUri;
    boolean coordinate = false;
    boolean bottle_check = false;
    boolean paperboard_check = false;
    private presenterValidate mValidate;

    //Firebase
    private StorageReference storageReference;
    private FirebaseFirestore firebaseStorage;
    private PreferenceM manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recycler);

        manager = new PreferenceM(this);

        mShowDialog = new ShowDialog(this);
        mShowAlert = new ShowAlert(this);
        mValidate = new presenterValidate(this);

        check_bottle = findViewById(R.id.check_bottle);
        check_paperboard = findViewById(R.id.check_paperboard);
        check_quantity_bottle = findViewById(R.id.check_quantity_bottle);
        check_quantity_paperboard = findViewById(R.id.check_quantity_paperboard);
        image_photo = findViewById(R.id.image_photo);
        stock_code = findViewById(R.id.stock_code);

        date_day = findViewById(R.id.date_day);
        get_location = findViewById(R.id.get_location);
        get_location.setVisibility(View.VISIBLE);

        get_location_ok = findViewById(R.id.get_location_ok);
        get_location_ok.setVisibility(View.GONE);

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseStorage = FirebaseFirestore.getInstance();

        findViewById(R.id.button_cancel).setOnClickListener(v -> onBackPressed());

        findViewById(R.id.button_Send).setOnClickListener(v -> verify_data());

        checkLocation();

        SET_DATE(); // SET DATE
    }

    private void checkLocation() {
        if (!hasManger().isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            mShowAlert.GPS_is_disabled();
        }
    }

    public void SET_DATE() {
        dateS = manager.getString(ConstantsUtility.KEY_CURRENT_DATE);
        Objects.requireNonNull(date_day.getEditText()).setText(dateS);
    }

    public void check_bottle(View view) {
        if (check_bottle.isChecked()) {
            bottle_check = true;
            check_quantity_bottle.setVisibility(View.VISIBLE);
        } else {
            bottle_check = false;
            check_quantity_bottle.setVisibility(View.GONE);
            Objects.requireNonNull(check_quantity_bottle.getEditText()).setText("");
        }
    }

    public void check_paperboard(View view) {
        if (check_paperboard.isChecked()) {
            paperboard_check = true;
            check_quantity_paperboard.setVisibility(View.VISIBLE);
        } else {
            paperboard_check = false;
            check_quantity_paperboard.setVisibility(View.GONE);
            Objects.requireNonNull(check_quantity_paperboard.getEditText()).setText("");
        }
    }

    private void verify_data() {
        if (coordinate) {
            if (currentPhotoPath != null && !currentPhotoPath.equals("")) {
                if (!mValidate.validateStockCodeEmpty(stock_code)) {
                    return;
                } else {
                    if (!mValidate.validateStockCode(stock_code)) return;
                }
                if (!bottle_check && !paperboard_check) {
                    mShowAlert.Quantity();
                    return;
                }
                if (bottle_check) {
                    if (!mValidate.validateStockCodeEmpty(check_quantity_bottle)) {
                        return;
                    }
                }
                if (paperboard_check) {
                    if (!mValidate.validateStockCodeEmpty(check_quantity_paperboard)) {
                        return;
                    }
                }
            } else {
                mShowAlert.photo();
                return;
            }
        } else {
            mShowAlert.coordinate();
            return;
        }
        send_data();
    }

    private void send_data() {

        codeS = Objects.requireNonNull(stock_code.getEditText()).getText().toString().trim();
        bottleS = Objects.requireNonNull(check_quantity_bottle.getEditText()).getText().toString().trim();
        paperboardS = Objects.requireNonNull(check_quantity_paperboard.getEditText()).getText().toString().trim();

        if (bottleS.equals("")) {
            bottleS = "0";
        }
        if (paperboardS.equals("")) {
            paperboardS = "0";
        }
        mShowDialog.SendingRequest();
        StorageReference image = storageReference.child("images/" + namePhoto);
        image.putFile(contentUri).addOnSuccessListener(taskSnapshot -> image.getDownloadUrl().addOnSuccessListener(uri -> insertData(uri.toString()))).addOnFailureListener(e -> {
            mShowDialog.DialogDismiss();
            mShowAlert.OnFailure();
        });
    }

    private void insertData(String photo) {

        recycleModel recycleModel = new recycleModel();
        recycleModel.setLatitude(latS);
        recycleModel.setLength(lenS);
        recycleModel.setDate(dateS);
        recycleModel.setPhoto(photo);
        recycleModel.setCode(codeS);
        recycleModel.setBottle(bottleS);
        recycleModel.setPaperboard(paperboardS);
        recycleModel.setState(getString(R.string.in_review));
        recycleModel.setPoints("0");

        String key = manager.getString(ConstantsUtility.KEY_USER);
        recycleModel.withId(key);

        firebaseStorage.collection("requests").add(recycleModel).addOnSuccessListener(documentReference -> {
            mShowDialog.DialogDismiss();
            Toast.makeText(this, getString(R.string.sent_request), Toast.LENGTH_SHORT).show();
            onBackPressed();
        }).addOnFailureListener(e -> {
            mShowDialog.DialogDismiss();
            mShowAlert.OnFailure();
        });
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED;
    }

    public void get_location(View view) {
        if (checkPermission()) {
            LocationManager manager = hasManger();
            requestLocationUpdates(manager);
        } else {
            requestLocation();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestLocationUpdates(LocationManager manager) {

        mShowDialog.LocationDialog();
        LocationListener listener = location -> {
            double lat = location.getLatitude();
            double longT = location.getLongitude();

            if (lat != 0 && longT != 0) {

                coordinate = true;
                latS = String.valueOf(lat);
                lenS = String.valueOf(longT);

                mShowDialog.DialogDismiss();
                get_location_ok.setVisibility(View.VISIBLE);
                get_location.setVisibility(View.GONE);
            }
            mShowDialog.DialogDismiss();
        };

        if (hasManger().isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 100, listener);
        } else {
            mShowDialog.DialogDismiss();
            mShowAlert.GPS_is_disabled();
        }
    }

    private void requestLocation() {
        mShowAlert.permission_request(LOCATION_PER_CODE, this);
    }

    private LocationManager hasManger() {
        return (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PER_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (!checkPermission()) {
                    requestLocation();
                }
            } else {
                requestLocation();
                Toast.makeText(this, getString(R.string.This_permission_location), Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, getString(R.string.camera_permission), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void capture_image(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
        } else {
            dispatchTakePictureIntent();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File f = new File(currentPhotoPath);
            image_photo.setImageURI(Uri.fromFile(f));
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
            namePhoto = f.getName();
        }
    }

    private File createImageFile() throws IOException {

        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                //noinspection deprecation
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
}